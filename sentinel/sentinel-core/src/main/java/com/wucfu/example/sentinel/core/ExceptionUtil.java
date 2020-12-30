package com.wucfu.example.sentinel.core;

import com.alibaba.cloud.sentinel.rest.SentinelClientHttpResponse;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;

/**
 * 限流时的异常处理配置
 * 应用启动的时候会检查 @SentinelRestTemplate 注解对应的限流或降级方法是否存在，如不存在会抛出异常
 */
public final class ExceptionUtil {

	private ExceptionUtil() {

	}

	/**
	 * 静态方法
	 */
	public static SentinelClientHttpResponse handleException(HttpRequest request,
                                                             byte[] body,
															 ClientHttpRequestExecution execution,
															 BlockException ex) {
		System.out.println("Oops: " + ex.getClass().getCanonicalName());
		return new SentinelClientHttpResponse("custom block info");
	}

}
