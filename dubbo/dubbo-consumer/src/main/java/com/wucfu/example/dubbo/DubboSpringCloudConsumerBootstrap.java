package com.wucfu.example.dubbo;

import com.wucfu.example.dubbo.service.User;
import com.wucfu.example.dubbo.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@EnableDiscoveryClient
@EnableAutoConfiguration
@EnableScheduling
@EnableCaching
@EnableFeignClients
public class DubboSpringCloudConsumerBootstrap {

	/** dubbo服务 */
	@Reference
	private UserService userService;

	public static void main(String[] args) {
		new SpringApplicationBuilder(DubboSpringCloudConsumerBootstrap.class)
				.properties("spring.profiles.active=nacos").run(args);
	}

	@Bean
	public ApplicationRunner userServiceRunner() {
		return arguments -> {

			User user = new User();
			user.setId(1L);
			user.setName("小马哥");
			user.setAge(33);

			// save User
			System.out.printf("UserService.save(%s) : %s\n", user,
					userService.save(user));

			// find all Users
			System.out.printf("UserService.findAll() : %s\n", user,
					userService.findAll());

			// remove User
			System.out.printf("UserService.remove(%d) : %s\n", user.getId(),
					userService.remove(user.getId()));

		};
	}

}
