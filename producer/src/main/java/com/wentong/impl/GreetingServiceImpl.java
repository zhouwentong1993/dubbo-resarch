package com.wentong.impl;


import com.wentong.api.service.GreetingService;
import com.wentong.api.util.Utils;
import com.wentong.api.vo.Result;
import org.apache.dubbo.common.json.JSON;
import org.apache.dubbo.rpc.RpcContext;

import java.io.IOException;

public class GreetingServiceImpl implements GreetingService {

    @Override
    public String sayHello(String name) {
        Utils.sleepSeconds(1);
        return "Hello " + name + " " + RpcContext.getContext().getAttachment("company");
    }

    @Override
    public Result<String> testGeneric(String name) {
        Result<String> result = new Result<>();
        result.setSuccess(true);
        try {
            result.setData(JSON.json(name));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
