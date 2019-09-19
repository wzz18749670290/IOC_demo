package com.wzz.demo.ioc.annotation;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 长按事件注解
 *
 * @author wangzhenzhou
 * @createTime 2019-09-03 11:37
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@EventType(setterMethod = "setOnLongClickListener",
        eventClazz = View.OnLongClickListener.class,
        actionMethod = "onLongClick")
public @interface OnLongClick {
    int[] value() default -1;
}
