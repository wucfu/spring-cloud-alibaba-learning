package com.wucfu.example.sentinel.feign.fallback;

import com.wucfu.example.sentinel.feign.service.EchoService;

/**
 * @author wucfu
 * @description sentinel 降级处理
 * @date 2020-09-30
 */
public class EchoServiceFallback implements EchoService {

    private Throwable throwable;

    EchoServiceFallback(Throwable throwable) {
        this.throwable = throwable;
    }

    /**
     * 调用服务提供方的输出接口.
     * @param str 用户输入
     * @return
     */
    @Override
    public String echo(String str) {
        return "consumer-fallback-default-str" + throwable.getMessage();
    }

}
