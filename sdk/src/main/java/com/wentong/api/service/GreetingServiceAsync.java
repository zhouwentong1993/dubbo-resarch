package com.wentong.api.service;

import java.util.concurrent.CompletableFuture;

public interface GreetingServiceAsync {

    CompletableFuture<String> hello(String name);

}
