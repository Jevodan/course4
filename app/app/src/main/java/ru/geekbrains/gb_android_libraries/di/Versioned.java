package ru.geekbrains.gb_android_libraries.di;

import javax.inject.Qualifier;
import javax.inject.Scope;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Qualifier
@Documented
@Retention(RUNTIME)
public @interface Versioned {

    /** The name. */
    int value() default 0;
}