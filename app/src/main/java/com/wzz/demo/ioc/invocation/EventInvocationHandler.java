package com.wzz.demo.ioc.invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 对象回调代理
 *
 * @author wangzhenzhou
 * @createTime 2019-09-03 13:09
 */
public class EventInvocationHandler implements InvocationHandler {
    private Object context;
    private Method method;

    public EventInvocationHandler(Object context, Method method) {
        this.context = context;
        this.method = method;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //这里调用对应context的添加事件注解的方法。注意方法参数需要与对应的event 的回调方法保持一致
        return this.method.invoke(context, args);
    }
}
