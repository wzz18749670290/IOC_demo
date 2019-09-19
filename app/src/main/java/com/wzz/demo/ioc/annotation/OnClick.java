package com.wzz.demo.ioc.annotation;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 点击事件类型注解
 *
 * @author wangzhenzhou
 * @createTime 2019-09-03 11:35
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@EventType(setterMethod = "setOnClickListener",
        eventClazz = View.OnClickListener.class,
        actionMethod = "onClick")
public @interface OnClick {
    int[] value() default -1;
}
