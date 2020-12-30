package com.wucfu.example.sentinel.feign.service;

import com.wucfu.example.sentinel.feign.fallback.EchoServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author wucfu
 * @description
 * @date 2020-09-30
 */
@FeignClient(name = "service-provider-wucfu",
        fallbackFactory = EchoServiceFallbackFactory.class)
public interface EchoService {

    /**
     * 调用服务提供方的输出接口.
     * @param str 用户输入
     * @return echo result
     */
    @GetMapping("/echo/{str}")
    String echo(@PathVariable("str") String str);

}
