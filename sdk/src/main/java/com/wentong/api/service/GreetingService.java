package com.wentong.api.service;

import com.wentong.api.vo.Result;

public interface GreetingService {

    String sayHello(String name);

    Result<String> testGeneric(String name);

}
