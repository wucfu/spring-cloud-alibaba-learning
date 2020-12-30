package com.wucfu.example.dubbo;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@EnableAutoConfiguration
public class DubboSpringCloudProviderBootstrap {

    public static void main(String[] args) {
        // 非web启动
       new SpringApplicationBuilder(DubboSpringCloudProviderBootstrap.class)
                .properties("spring.profiles.active=nacos").web(WebApplicationType.NONE)
                .run(args);
    }

}