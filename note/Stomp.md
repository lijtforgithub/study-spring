使用 @MessageMapping 或者 @SubscribeMapping 注解可以处理客户端发送过来的消息，并选择方法是否有返回值。  
- @SubscribeMapping 标注的方法，只会处理SUBSCRIBE发送的消息。
- @MessageMapping 标注的方法，只会处理SEND发送的消息。

@MessageMapping 指定目的地是 /app/message（/app 前缀是隐含的，因为我们将其配置为应用的目的地前缀）  
@MessageMapping 注解的控制器方法有返回值的话，返回值会被发送到消息代理，只不过会添加上 /topic 前缀。可以使用@SendTo 重写消息目的地  

如果客户端就是想要服务端直接返回消息呢？听起来不就是HTTP做的事情！即使这样，STOMP 仍然为这种一次性的响应提供了支持，用的是@SubscribeMapping注解，与HTTP不同的是，这种请求-响应模式是异步的  
@SubscribeMapping 注解的控制器方法有返回值的话，返回值会直接发送到客户端，不经过代理。如果加上@SendTo 注解的话，则要经过消息代理。
