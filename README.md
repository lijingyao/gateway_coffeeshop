# api-gateway project example 


## 简介

本Demo需要结合 [microservice_coffeeshop](https://github.com/lijingyao/microservice_coffeeshop) 微服务工程。
 
API-Gateway 主要为了解决微服务架构下，如何提高多个服务的请求效率以及数据转发、微服务间解耦等问题。    
Gateway是所有客户端请求的入口。类似Facade模式。为了提高请求的性能，最好选择一套非阻塞I/O的框架。本Demo基于RxJava实现。 


主要使用的基础技术如下：      



* Gateway服务发现使用EurekaServer。 EurekaServer的Docker镜像可以从 dockerhub 中download。       
* 响应式框架： [RxJava](https://github.com/ReactiveX/RxJava) 
* 使用的工程构建工具：Gradle、Gradlew插件    
* 涉及到的框架有：SpringMVC、SpringBoot、SpringCloud-Netflix Eureka\Feign\Ribbon、     
* 服务间通信：Restful API   
* 服务基础涉及原则：DDD(Domain-Driven Design)     

其他：

1. 编码：utf-8
2. 服务部署，可以参考  [microservice_coffeeshop](https://github.com/lijingyao/microservice_coffeeshop) 中介绍的方式。


### 给读者的作业  

1. 本Demo中的 **UserSimpleOrderModel** 返回的是一个类似  "用户个人中心"，目前只有user信息以及
最近购买的订单信息。请试着用 RxJava 在订单信息中添加子订单的数据，以及子订单中的商品信息。注意订单->商品
是需要使用同步 flatmap 的。  
2. API-Gateway 是一个api的统一出口，对于restful 风格的api来说，为了提高开发效率，可以使用 [swagger](https://swagger.io/)
来做API 的信息管理，方便前后端联调、测试。本工程中，读者可以尝试自己checkout下来添加下Swagger的配置。     

 
