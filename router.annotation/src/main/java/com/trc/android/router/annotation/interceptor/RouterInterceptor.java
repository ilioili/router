package com.trc.android.router.annotation.interceptor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RouterInterceptor {
    Class[] value();
}
