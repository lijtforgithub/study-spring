package com.ljt.study.servlet3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * @author LiJingTang
 * @date 2021-07-18 13:17
 */
class AsyncController {

    /**
     * 1. controller 返回 Callable
     * 2. spring 异步处理 将 Callable 提交到 TaskExecutor 使用一个隔离的线程执行
     * 3. DispatcherServlet 和所有的 Filter 退出web容器的线程 但是response 保持打开状态
     * 4. Callable 返回结果 springMvc 将请求重新派发给容器 恢复之前的处理
     * 5. 根据 Callable 返回的结果 springMvc 继续进行视图渲染流程
     *
     * preHandle
     * 主线程
     * 副线程
     * preHandle
     * postHandle
     * afterCompletion
     *
     * 异步拦截器
     * 1. 原生API：AsyncListener
     * 2. spring: AsyncHandlerInterceptor
     */
    @GetMapping("/call")
    public Callable<String> call() {
        return () -> "async: callable";
    }

    @GetMapping("/deferred")
    public DeferredResult<String> deferred() {
        DeferredResult<String> result = new DeferredResult<>();
        Executors.newSingleThreadExecutor().submit(() -> result.setResult("async: deferredResult"));
        return result;
    }

}
