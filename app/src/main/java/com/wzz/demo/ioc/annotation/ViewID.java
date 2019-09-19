package com.wzz.demo.ioc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * widget id 注解
 *
 * @author wangzhenzhou
 * @createTime 2019-09-03 11:31
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ViewID {
    int value() default -1;
}
