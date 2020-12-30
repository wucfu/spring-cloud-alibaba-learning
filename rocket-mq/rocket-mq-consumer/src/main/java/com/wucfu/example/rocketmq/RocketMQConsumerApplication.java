
package com.wucfu.example.rocketmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.binder.PollableMessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.messaging.SubscribableChannel;

@SpringBootApplication
@EnableBinding({RocketMQConsumerApplication.MySink.class})
public class RocketMQConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketMQConsumerApplication.class, args);
    }

    @Bean
    public ConsumerCustomRunner customRunner() {
        return new ConsumerCustomRunner();
    }

	/**
	 * @Input 注解声明了Binding,与配置文件中的spring.cloud.stream.bindings 配置项对应
	 * 声明了input，才能在@StreamListener中使用input
	 * 而input的实现是Spring Cloud Stream 的 BindableProxyFactory 通过动态代理实现
	 */
	public interface MySink {

		/**
		 * 配置：
		 * 顺序消费
		 */
        @Input("input1")
        SubscribableChannel input1();

		/**
		 * 配置：
		 * tag: tagStr
		 */
        @Input("input2")
        SubscribableChannel input2();

		/**
		 * 配置：
		 * tags: tagObj
		 */
        @Input("input3")
        SubscribableChannel input3();

		/**
		 * 配置：
		 * 消费TransactionTopic
		 */
        @Input("input4")
        SubscribableChannel input4();

		/**
		 * 配置：
		 * 消费pull-topic
         * input5没有配置对应的StreamListener
         * 在后面程序中，采用poll方式拉取消息
		 */
		@Input("input5")
        PollableMessageSource input5();

    }

    public static class ConsumerCustomRunner implements CommandLineRunner {

        @Autowired
        private MySink mySink;

        @Override
        public void run(String... args) throws InterruptedException {
            while (true) {
                mySink.input5().poll(m -> {
                    String payload = (String) m.getPayload();
                    System.out.println("pull msg: " + payload);
                }, new ParameterizedTypeReference<String>() {
                });
                Thread.sleep(2_000);
            }
        }

    }

}
