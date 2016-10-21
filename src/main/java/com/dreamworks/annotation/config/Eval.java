package com.dreamworks.annotation.config;

import java.lang.annotation.*;

/**
 * Created by mmonti on 10/20/16.
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Eval {
}
