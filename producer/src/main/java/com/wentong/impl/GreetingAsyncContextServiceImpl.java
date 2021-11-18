package com.wentong.impl;

import com.wentong.api.service.GreetingService;
import com.wentong.api.service.GreetingServiceAsync;

import java.util.concurrent.CompletableFuture;

/**
 * 基于 RpcContext 实现的异步调用
 */
public class GreetingAsyncContextServiceImpl implements GreetingServiceAsync {

    @Override
    public CompletableFuture<String> hello(String name) {
        return null;
    }
}
