package com.wucfu.example.sentinel.feign.fallback;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author wucfu
 * @description
 * @date 2020-09-30
 */
@Component
public class EchoServiceFallbackFactory implements FallbackFactory<EchoServiceFallback> {

    @Override
    public EchoServiceFallback create(Throwable throwable) {
        return new EchoServiceFallback(throwable);
    }

}
