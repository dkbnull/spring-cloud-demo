package cn.wbnull.loadbalancer;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.DefaultResponse;
import org.springframework.cloud.client.loadbalancer.EmptyResponse;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 自定义负载均衡策略类
 *
 * @author dukunbiao(null)  2019-04-24
 *         https://github.com/dkbnull/SpringCloudDemo
 */
@RequiredArgsConstructor
public class GlobalRule implements ReactorServiceInstanceLoadBalancer {

    private final ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider;

    @Override
    public Mono<Response<ServiceInstance>> choose(Request request) {
        ServiceInstanceListSupplier supplier = serviceInstanceListSupplierProvider.getIfAvailable();
        if (supplier == null) {
            return Mono.just(new EmptyResponse());
        }

        return supplier.get().next().map(this::chooseFirstInstance);
    }

    private Response<ServiceInstance> chooseFirstInstance(List<ServiceInstance> serviceInstances) {
        if (serviceInstances.isEmpty()) {
            return new EmptyResponse();
        }

        return new DefaultResponse(serviceInstances.get(0));
    }
}