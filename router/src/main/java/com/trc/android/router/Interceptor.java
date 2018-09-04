package com.trc.android.router;

public interface Interceptor {
    /**
     * @param chain 执行chain.proceed(router)会继续router的分发流程(交给下一个Interceptor 或 最终的目标Class处理)
     */
    void handle(Router router, Chain chain);

}
