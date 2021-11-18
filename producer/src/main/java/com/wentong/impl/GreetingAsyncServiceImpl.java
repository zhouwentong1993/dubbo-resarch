package com.wentong.impl;

import com.wentong.api.service.GreetingServiceAsync;
import com.wentong.api.util.Utils;
import org.apache.dubbo.common.utils.NamedThreadFactory;
import org.apache.dubbo.rpc.RpcContext;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 基于自定义线程池实现的异步调用
 */
public class GreetingAsyncServiceImpl implements GreetingServiceAsync {

    //1）创建业务自定义线程池
    private final ThreadPoolExecutor bizThreadPool = new ThreadPoolExecutor(8, 16, 1,
            TimeUnit.MINUTES,
            new SynchronousQueue<>(), new NamedThreadFactory("biz-thread-pool"), new ThreadPoolExecutor.CallerRunsPolicy());

    @Override
    public CompletableFuture<String> hello(String name) {

        RpcContext context = RpcContext.getContext();

        return CompletableFuture.supplyAsync(() -> {
            Utils.sleepSeconds(2);
            System.out.println("异步调用触发");
            return "Hello " + name + " " + context.getAttachment("company");
        }, bizThreadPool);
    }
}
