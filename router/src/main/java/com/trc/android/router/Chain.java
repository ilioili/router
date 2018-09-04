package com.trc.android.router;

public interface Chain {
    /**
     * 继续Router的分发流程(交给下一个Interceptor 或 最终的目标Class处理)
     */
    void proceed(Router router);
}
