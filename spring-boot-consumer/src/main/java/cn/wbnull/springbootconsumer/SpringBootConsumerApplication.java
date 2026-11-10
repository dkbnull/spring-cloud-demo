package cn.wbnull.springbootconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Spring Boot 启动类
 *
 * @author dukunbiao(null)  2019-04-12
 *         https://github.com/dkbnull/SpringCloudDemo
 */
@SpringBootApplication
@EnableEurekaClient
@LoadBalancerClient(value = "spring-boot-provider", configuration = cn.wbnull.loadbalancer.GlobalLoadBalancerConfig.class)
public class SpringBootConsumerApplication {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootConsumerApplication.class, args);
    }
}
