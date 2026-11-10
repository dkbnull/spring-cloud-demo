package cn.wbnull.loadbalancer;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * 负载均衡策略配置类
 *
 * @author dukunbiao(null)  2019-04-24
 *         https://github.com/dkbnull/SpringCloudDemo
 */
@Configuration
public class GlobalLoadBalancerConfig {

    @Bean
    public ReactorLoadBalancer<ServiceInstance> globalRule(Environment environment, LoadBalancerClientFactory loadBalancerClientFactory) {
        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);

        return new GlobalRule(loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class));
    }
}
