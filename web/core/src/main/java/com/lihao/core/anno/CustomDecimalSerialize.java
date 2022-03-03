package com.yscredit.ys.indchain.common.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yscredit.ys.indchain.common.config.CustomDecimalSerializeConfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.math.BigDecimal;

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
