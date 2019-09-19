package com.wzz.demo.ioc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 事件参数枚举
 *
 * @author wangzhenzhou
 * @createTime 2019-09-03 11:32
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface EventType {
    String setterMethod();

    Class<?> eventClazz();

    String actionMethod();
}
