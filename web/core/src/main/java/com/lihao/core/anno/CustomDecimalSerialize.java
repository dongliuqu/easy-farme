package com.lihao.core.anno;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lihao.core.config.CustomDecimalSerializeConfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义的bigDecimal注解
 */
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = CustomDecimalSerializeConfig.class)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface CustomDecimalSerialize {

    /**
     * 保留小数位
     *
     * @return
     */
    int scale() default 2;

    /**
     * 缩小幅度
     *
     * @return
     */
    String reduction() default "1";
}
