package br.com.elementi.core.annotation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = {FIELD, METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@QSearchAnnotation
public @interface QSearchLike {

    String value() default "";

}
