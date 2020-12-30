
package com.wucfu.example.sentinel.core;

import com.alibaba.cloud.circuitbreaker.sentinel.SentinelCircuitBreakerFactory;
import com.alibaba.cloud.circuitbreaker.sentinel.SentinelConfigBuilder;
import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@SpringBootApplication
public class ServiceApplication {

	/**
	 * 对 RestTemplate 的服务调用使用 Sentinel 进行保护，在构造 RestTemplate bean的时，加上 @SentinelRestTemplate 注解
	 * 支持限流(blockHandler, blockHandlerClass)和降级(fallback, fallbackClass)的处理
	 * 其中 blockHandler 或 fallback 属性对应的方法必须是对应 blockHandlerClass 或 fallbackClass 属性中的静态方法
	 * 该方法的参数跟返回值跟 org.springframework.http.client.ClientHttpRequestInterceptor#interceptor 方法一致，
	 * 其中参数多出了一个 BlockException 参数用于获取 Sentinel 捕获的异常
	 * (blockHandler, blockHandlerClass)和降级(fallback, fallbackClass)属性不强制填写
	 */
	@Bean
	@SentinelRestTemplate(blockHandler = "handleException",
			blockHandlerClass = ExceptionUtil.class)
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	/**
	 * 普通的restTemplate
	 */
	@Bean
	public RestTemplate restTemplate2() {
		return new RestTemplate();
	}

	@Bean
	public Converter myConverter() {
		return new JsonFlowRuleListConverter();
	}

	@Bean
	public Customizer<SentinelCircuitBreakerFactory> defaultConfig() {
		return factory -> {
			factory.configureDefault(
					id -> new SentinelConfigBuilder().resourceName(id)
							.rules(Collections.singletonList(new DegradeRule(id)
									.setGrade(RuleConstant.DEGRADE_GRADE_RT).setCount(100)
									.setTimeWindow(10)))
							.build());
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}

}
