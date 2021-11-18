package com.wentong.consumer;

import com.wentong.api.service.GreetingService;
import com.wentong.api.service.GreetingServiceAsync;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;

import java.util.concurrent.CompletableFuture;

public class ApiAsyncConsumer {

    public static void main(String[] args) throws Exception {
// 10.创建服务引用对象实例
        ReferenceConfig<GreetingService> referenceConfig = new ReferenceConfig<>();
        // 11.设置应用程序信息
        referenceConfig.setApplication(new ApplicationConfig("first-dubbo-consumer"));
        // 12.设置服务注册中心
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));

        //直连测试
        //referenceConfig.setUrl("dubbo://192.168.0.109:20880");

        // 13.设置服务接口和超时时间
        referenceConfig.setInterface(GreetingService.class);
        referenceConfig.setTimeout(3000);

        // 14.设置自定义负载均衡策略与集群容错策略
        RpcContext.getContext().set("company", "30.10.67.231");

        // 15.设置服务分组与版本
        referenceConfig.setVersion("1.0.0");
        referenceConfig.setGroup("dubbo");
        referenceConfig.setAsync(true);

        // 16.引用服务
        GreetingService greetingService = referenceConfig.get();

        // 17. 设置隐式参数
        RpcContext.getContext().setAttachment("company", "alibaba");

        // 18调用服务
        String hello = greetingService.sayHello("world");
        System.out.println(hello);

        CompletableFuture<String> future = RpcContext.getContext().getCompletableFuture();
        future.whenComplete((v, t) -> {
            if (t == null) {
                System.out.println(v);
            } else {
                t.printStackTrace();
            }
        });
        Thread.currentThread().join();
    }

}
