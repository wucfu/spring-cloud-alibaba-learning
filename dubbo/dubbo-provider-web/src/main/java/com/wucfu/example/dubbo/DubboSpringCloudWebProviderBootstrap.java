package com.wucfu.example.dubbo;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@EnableAutoConfiguration
public class DubboSpringCloudWebProviderBootstrap {

    public static void main(String[] args) {
        new SpringApplicationBuilder(DubboSpringCloudWebProviderBootstrap.class)
                .properties("spring.profiles.active=nacos").run(args);
    }

}