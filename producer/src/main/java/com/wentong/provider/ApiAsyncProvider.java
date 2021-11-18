package com.wentong.provider;

import com.wentong.api.service.GreetingServiceAsync;
import com.wentong.impl.GreetingAsyncServiceImpl;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

/**
 * 异步的服务提供方
 */
public class ApiAsyncProvider {

    public static void main(String[] args) throws Exception {
        // 1.创建服务发布实例，并设置
        ServiceConfig<GreetingServiceAsync> serviceConfig = new ServiceConfig<>();
        serviceConfig.setApplication(new ApplicationConfig("first-dubbo-provider"));
        serviceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        serviceConfig.setInterface(GreetingServiceAsync.class);
        serviceConfig.setRef(new GreetingAsyncServiceImpl());
        serviceConfig.setVersion("1.0.0");
        serviceConfig.setGroup("dubbo");

        // 2.设置线程池策略
        // HashMap<String, String> parameters = new HashMap<>();
        // parameters.put("threadpool", "mythreadpool");
        // serviceConfig.setParameters(parameters);

        // 3.导出服务
        serviceConfig.export();

        // 4.阻塞线程
        System.out.println("server is started");
        System.in.read();
    }

}
