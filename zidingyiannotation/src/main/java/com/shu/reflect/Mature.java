package com.shu.reflect;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ ElementType.FIELD, ElementType.METHOD })//允许属性和方法
@Retention(RetentionPolicy.RUNTIME)//允许通过反射加载，
public @interface Mature {

    String value() default "";

}