package com.manappuram.ogl.util.custom_annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DisplayString {
    int value() default 0;
}
