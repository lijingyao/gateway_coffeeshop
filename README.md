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

### 解答

1. 更新代码后，可以看到UserSimpleOrderModel增加了子订单的模型。造数据的时候，需要先创建商品、用户信息。创建好之后：       
创建订单的POST请求，可以按照如下格式请求：

```

POST url:http://localhost:8098/orders    


request body:
{
	"userId":1,
	"details":[
		{"itemId":1,
		"quantity":2,
		"sugar":"HALF",
		"milk":"MORE",
		"coffeine":"DE_CAFE",
		"espresso":1
		},{
		"itemId":2,
		"quantity":1,	
		"sugar":"HALF",
		"milk":"NONE",
		"coffeine":"DE_CAFE"
		},{
		"itemId":3,
		"quantity":1,	
		"sugar":"HALF",
		"milk":"NONE",
		"coffeine":"DE_CAFE"
		}
	]
}


```

然后请求用户的个人中心接口如下：

```

GET url: http://localhost:8098/users/center/1     


response body:

{
    "errors": null,
    "result": {
        "userId": 1,
        "registeredTime": 1531052851000,
        "nickName": "bbb",
        "orderModels": [
            {
                "orderId": "101071be9e67a7152ca901",
                "orderPrice": 152,
                "createTime": 1533627611000,
                "detail": {
                    "mainOrderId": "101071be9e67a7152ca901",
                    "itemId": 1,
                    "price": 78,
                    "quantity": 2,
                    "itemName": "NEW la",
                    "additional": "1|1|HALF|MORE"
                }
            },
            {
                "orderId": "101071be9e407d13cbe701",
                "orderPrice": 115,
                "createTime": 1533627551000,
                "detail": {
                    "mainOrderId": "101071be9e407d13cbe701",
                    "itemId": 1,
                    "price": 78,
                    "quantity": 2,
                    "itemName": "NEW la",
                    "additional": "1|1|HALF|MORE"
                }
            },
            {
                "orderId": "101071be9d7bc321f5ee01",
                "orderPrice": 115,
                "createTime": 1533627247000,
                "detail": {
                    "mainOrderId": "101071be9d7bc321f5ee01",
                    "itemId": 1,
                    "price": 78,
                    "quantity": 2,
                    "itemName": "NEW la",
                    "additional": "1|1|HALF|MORE"
                }
            },
            {
                "orderId": "101071be9d11cd141f3801",
                "orderPrice": 107,
                "createTime": 1533627096000,
                "detail": {
                    "mainOrderId": "101071be9d11cd141f3801",
                    "itemId": 1,
                    "price": 70,
                    "quantity": 2,
                    "itemName": "NEW la",
                    "additional": "-|0|HALF|MORE"
                }
            },
            {
                "orderId": "10106f7c66b8901cba1d01",
                "orderPrice": 140,
                "createTime": 1531205530000,
                "detail": {
                    "mainOrderId": "10106f7c66b8901cba1d01",
                    "itemId": 3,
                    "price": 38,
                    "quantity": 1,
                    "itemName": "SOFTCM",
                    "additional": "1|-|HALF|-"
                }
            }
        ]
    },
    "message": null,
    "success": true
}

```   


2. 添加Swagger信息，需要先有Swagger服务。然后只要将 *http://localhost:8098/api-info* 输入后explore即可。
可以看到每个Controller接口都新增了 Swagger 的Api信息。配置类可以查看 **SwaggerConfig** 。   



## Gitchat 课程



购买课程的读者可以在读者圈提问或留言。没有购买的读者也可以在 Github 上提 Issues 一起探讨。感谢各位支持 OvO。   
 
课程地址:      
[分布式微服务架构体系详解](https://gitbook.cn/gitchat/column/5b444ae694c0f60b4ec4a68c)
  
Gitchat chat地址：  
[基于 Docker 的微服务架构实践](https://gitbook.cn/gitchat/activity/5a425b957431432eb6052297)


![分布式微服务架构体系详解](https://images.gitbook.cn/618135a0-adaf-11e8-be67-898fbb02efe9)
