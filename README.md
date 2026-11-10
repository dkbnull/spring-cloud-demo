<h1 align="center">
  Spring Cloud Demo
</h1>

<p align="center">
  <a href="https://github.com/dkbnull/spring-cloud-demo" target="_blank">
     <img src="https://img.shields.io/badge/GitHub-仓库-blue?logo=github">
  </a>
  <a href="https://gitee.com/dkbnull/spring-cloud-demo" target="_blank">
     <img src="https://img.shields.io/badge/Gitee-仓库-red?logo=gitee">
  </a>
  <img src="https://img.shields.io/badge/License-MIT-yellow">
</p>

## 开发环境

- JDK：1.8
- Spring Boot：2.7.18
- Spring Cloud：2021.0.9
- Spring Cloud Netflix（Hystrix、Zuul）：2.2.10.RELEASE

## 模块说明

| 模块 | 端口 | 上下文路径 | 说明 |
| --- | --- | --- | --- |
| spring-cloud-eureka | 8090 | /springcloudeureka | 服务注册与发现中心 |
| spring-boot-provider | 8081 | /springbootprovider | 服务提供者，集成 Swagger2 |
| spring-boot-provider-v2 | 8083 | /springbootprovider | 服务提供者第二实例，用于验证负载均衡 |
| spring-boot-consumer | 8082 | /springbootconsumer | RestTemplate 调用，自定义负载均衡策略 |
| spring-boot-consumer-feign | 8084 | /springbootconsumer | Feign 调用，自定义 Contract |
| spring-boot-consumer-hystrix | 8085 | /springbootconsumer | RestTemplate + @HystrixCommand 熔断 |
| spring-boot-consumer-feign-hystrix | 8086 | /springbootconsumer | Feign + fallbackFactory 熔断 |
| spring-cloud-zuul | 8091 | /springcloudzuul | 路由网关、token 过滤器、Swagger 聚合 |
| spring-cloud-config-server | 8094 | /springcloudconfig | 分布式配置中心服务端（Git 仓库） |
| spring-cloud-config-client | 8093 | /springcloudconfig | 分布式配置中心客户端 |
| spring-cloud-zipkin | 8092 | - | 链路追踪服务端 |

## 启动顺序

1. spring-cloud-eureka（8090）
2. spring-cloud-config-server（8094）
3. spring-cloud-config-client（8093）
4. spring-cloud-zipkin（8092）
5. spring-boot-provider（8081）、spring-boot-provider-v2（8083）
6. spring-boot-consumer（8082）、spring-boot-consumer-feign（8084）、spring-boot-consumer-hystrix（8085）、spring-boot-consumer-feign-hystrix（8086）
7. spring-cloud-zuul（8091）

## 接口验证

| 场景 | 请求 |
| --- | --- |
| Eureka 控制台 | GET http://localhost:8090/springcloudeureka/ |
| 服务提供者 | GET http://localhost:8081/springbootprovider/gateway |
| 自定义负载均衡 | GET http://localhost:8082/springbootconsumer/gateway |
| Feign | GET http://localhost:8084/springbootconsumer/gateway |
| Hystrix 熔断 | POST http://localhost:8085/springbootconsumer/users |
| Feign + Hystrix | POST http://localhost:8086/springbootconsumer/users |
| Zuul 路由 | POST http://localhost:8091/springcloudzuul/zuul-a/users，需携带请求头 token |
| Zuul 聚合 Swagger | GET http://localhost:8091/springcloudzuul/v2/api-docs |
| 配置中心客户端 | GET http://localhost:8093/springcloudconfig/gateway |
| Zipkin 控制台 | GET http://localhost:8092 |

## 升级说明

博客文章基于 Spring Boot 2.0.9 / Finchley 编写，代码已适配 Spring Boot 2.7.18 / 2021.0.9，主要差异如下：

- **bootstrap.yml 默认不再生效**：Spring Cloud 2020.0.0 起需引入 `spring-cloud-starter-bootstrap` 才会创建 bootstrap 上下文，配置中心客户端必须显式添加该依赖。
- **Ribbon 已被移除**：改用 Spring Cloud LoadBalancer，通过 `@LoadBalancerClient` 指定配置类，自定义策略实现 `ReactorServiceInstanceLoadBalancer`；原 `ribbon.*` 超时配置改为 `feign.client.config.*`。
- **Springfox 2.10.5 与 Boot 2.6+ 不兼容**：需设置 `spring.mvc.pathmatch.matching-strategy: ant_path_matcher`，否则启动时 `documentationPluginsBootstrapper` 报空指针。
- **Hystrix、Zuul 已停止维护**：不再由 2021.0.9 的 BOM 管理，由父 POM 的 `spring-cloud-netflix-legacy.version` 单独锁定为 2.2.10.RELEASE；`spring-cloud-starter-netflix-hystrix` 为 optional 依赖，使用熔断能力时必须显式引入，否则 `feign.hystrix.enabled` 静默失效。
- **Zipkin 服务端内嵌模式已不推荐**：`@EnableZipkinServer` 在 Boot 2.4+ 下与自动装配冲突，生产环境建议直接运行 Zipkin 官方可执行 jar，本模块仅作演示。
- **配置中心端口调整为 8094**：原 8092 与 Zipkin 冲突。

## Spring Cloud 专栏

[CSDN](https://blog.csdn.net/dkbnull/category_9287932.html) · [知乎](https://zhuanlan.zhihu.com/c_1105052885779513344) · [微博](https://weibo.com/a/hot/7581491716986881_1.html)

## 相关文章

| 文章 | 阅读 |
| --- | --- |
| Spring Cloud 整合 Spring Boot（服务提供者和服务消费者） | [CSDN](https://blog.csdn.net/dkbnull/article/details/89223691) · [知乎](https://zhuanlan.zhihu.com/p/63925470) · [微信](https://mp.weixin.qq.com/s/7ZrgoRyAcs7agzpVtsWe4w) · [微博](https://weibo.com/ttarticle/p/show?id=2309404365477253117454) |
| Spring Cloud 服务注册与发现 Eureka | [CSDN](https://blog.csdn.net/dkbnull/article/details/89268194) · [知乎](https://zhuanlan.zhihu.com/p/64284070) · [微信](https://mp.weixin.qq.com/s/L4WVLGh7d6ULl9bP6IdxVg) · [微博](https://weibo.com/ttarticle/p/show?id=2309404366699653683350) |
| Spring Cloud 自定义 Eureka Ribbon 负载均衡策略 | [CSDN](https://blog.csdn.net/dkbnull/article/details/89506462) · [知乎](https://zhuanlan.zhihu.com/p/64760029) · [微信](https://mp.weixin.qq.com/s/D3wHuZiuhqbqXI5MudwxMg) · [微博](https://weibo.com/ttarticle/p/show?id=2309404368874400313637) |
| Spring Cloud Feign 的使用和自定义配置 | [CSDN](https://blog.csdn.net/dkbnull/article/details/89525792) · [知乎](https://zhuanlan.zhihu.com/p/65008318) · [微信](https://mp.weixin.qq.com/s/RL8ZtwH3x6XZLzE-pYj6dA) · [微博](https://weibo.com/ttarticle/p/show?id=2309404369596210654515) |
| Spring Cloud 熔断器/断路器 Hystrix | [CSDN](https://blog.csdn.net/dkbnull/article/details/89578323) · [知乎](https://zhuanlan.zhihu.com/p/65133651) · [微信](https://mp.weixin.qq.com/s/VvuyF5JtYmA0dDyGD4uNIw) · [微博](https://weibo.com/ttarticle/p/show?id=2309404369957537327375) |
| Spring Cloud 路由网关和过滤器 Zuul | [CSDN](https://blog.csdn.net/dkbnull/article/details/89736893) · [知乎](https://zhuanlan.zhihu.com/p/65360773) · [微信](https://mp.weixin.qq.com/s/lWMudofECNpskWTioI2cIg) · [微博](https://weibo.com/ttarticle/p/show?id=2309404370511336471294) |
| Spring Cloud Config 分布式配置中心 | [CSDN](https://blog.csdn.net/dkbnull/article/details/89934484) · [知乎](https://zhuanlan.zhihu.com/p/65654453) · [微信](https://mp.weixin.qq.com/s/jcFZUliJ5Fq5MSQWQNombw) · [微博](https://weibo.com/ttarticle/p/show?id=2309404371771666085647) |
| Spring Cloud 全链路跟踪 Zipkin | [CSDN](https://blog.csdn.net/dkbnull/article/details/93928005) · [知乎](https://zhuanlan.zhihu.com/p/71162391) · [微信](https://mp.weixin.qq.com/s/03ffLZfep8ZU6hgy8g_tQg) · [微博](https://weibo.com/ttarticle/p/show?id=2309404387944860409936) |
| Spring Cloud Zuul 整合 Swagger2 | [CSDN](https://blog.csdn.net/dkbnull/article/details/97042333) · [知乎](https://zhuanlan.zhihu.com/p/74902374) · [微信](https://mp.weixin.qq.com/s/Mf7iVHd2cEN4UMVz0gAUHQ) · [微博](https://weibo.com/ttarticle/p/show?id=2309404397344069124460) |