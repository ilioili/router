package com.trc.android.router.annotation.uri;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RouterUri {
    String[] value();
}
