package com.wzz.demo.ioc.inject;

import android.view.View;

import com.wzz.demo.ioc.annotation.ContentView;
import com.wzz.demo.ioc.annotation.EventType;
import com.wzz.demo.ioc.annotation.ViewID;
import com.wzz.demo.ioc.invocation.EventInvocationHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * IOC 注入工具类
 *
 * @author wangzhenzhou
 * @createTime 2019-09-03 11:42
 */
public class InjectUtils {
    public static void inject(Object context) {
        injectContentView(context);
        injectView(context);
        injectEvent(context);
    }

    /**
     * 注入布局
     *
     * @param context
     */
    private static void injectContentView(Object context) {
        ContentView contentViewAnnotation = context.getClass().getAnnotation(ContentView.class);
        //context没有添加ContentView注解，return
        if (contentViewAnnotation == null) {
            return;
        }
        //获取注解value
        int layoutId = contentViewAnnotation.value();
        //如果注解value为默认值[写注解了，但是没填写值]，return
        if (layoutId == -1) {
            return;
        }
        try {
            //找到context的setContentView方法
            Method setContentView = context.getClass().getMethod("setContentView", int.class);
            //调用setContentView方法
            setContentView.invoke(context, layoutId);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 注入View
     *
     * @param context
     */
    private static void injectView(Object context) {
        //获取到context定义的字段
        Field[] declaredFields = context.getClass().getDeclaredFields();
        try {
            //拿到context 的findViewById方法
            Method findViewById = context.getClass().getMethod("findViewById", int.class);
            for (Field declaredField : declaredFields) {
                //拿到Field对应的ViewID类型的注解
                ViewID viewIDAnnotation = declaredField.getAnnotation(ViewID.class);
                //如果注解不存在，跳过
                if (viewIDAnnotation != null) {
                    //拿到注解值
                    int viewId = viewIDAnnotation.value();
                    //如果值为默认值，忽略，继续下次循环
                    if (viewId == -1) {
                        continue;
                    }
                    //调用findViewById方法得到View
                    View view = (View) findViewById.invoke(context, viewId);
                    //设置字段的访问权限
                    declaredField.setAccessible(true);
                    //设置字段值
                    declaredField.set(context, view);
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    /**
     * 注入事件
     *
     * @param context
     */
    private static void injectEvent(Object context) {
        try {
            //获取context定义的方法
            Method[] declaredMethods = context.getClass().getDeclaredMethods();
            //获取到context的findViewById方法
            Method findViewById = context.getClass().getMethod("findViewById", int.class);
            for (Method declaredMethod : declaredMethods) {
                //获取方法所添加的注解
                Annotation[] methodAnnotations = declaredMethod.getAnnotations();
                for (Annotation methodAnnotation : methodAnnotations) {
                    //获取作用在注解上面的EventType类型的注解
                    EventType eventAnnotation = methodAnnotation.annotationType().getAnnotation(EventType.class);
                    //如果eventAnnotation为空说明不是事件类型的注解，继续下次循环
                    if (eventAnnotation == null) {
                        continue;
                    }
                    //view中的set方法
                    String setterMethod = eventAnnotation.setterMethod();
                    //event事件 class
                    Class<?> eventClazz = eventAnnotation.eventClazz();
                    // event事件回调方法
                    String actionMethod = eventAnnotation.actionMethod();


                    //获取到事件类型注解的value方法
                    Method valueMethod = methodAnnotation.getClass().getDeclaredMethod("value");
                    //获取到value值【widget的id数组】
                    int[] widgetIds = (int[]) valueMethod.invoke(methodAnnotation);
                    for (int widgetId : widgetIds) {
                        //根据findViewById方法获取到对应的view
                        View view = (View) findViewById.invoke(context, widgetId);
                        //如果找不到view，继续下次循环
                        if (view == null) {
                            continue;
                        }
                        //创建一个动态代理，用于view的事件代理
                        Object proxyInstance = Proxy.newProxyInstance(context.getClass().getClassLoader(), new Class[]{eventClazz}, new EventInvocationHandler(context, declaredMethod));
                        //获取到事件对应的Method
                        Method setterMethod1 = view.getClass().getMethod(setterMethod, eventClazz);
                        //将代理与Method方法建立关联
                        setterMethod1.invoke(view, proxyInstance);
                    }
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
