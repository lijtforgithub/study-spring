/**
 * @author LiJingTang
 * @version 2015年9月30日 下午7:56:16
 */
package com.ljt.study.jms;

/**
	JMS的功能大致上分为两块，叫做消息制造和消息消耗。JmsTemplate 用于制造消息和同步消息接收。
	
	Spring模板类的公共设计原则就是通过提供助手方法去执行公共的操作，并将实际的处理任务委派到用户实现的回调接口上，从而完成更复杂的操作。
	
	SimpleMessageListenerContainer DefaultMessageListenerContainer ServerSessionMessageListenerContainer
	
	JmsTemplate 类的实例 一经配置便是线程安全 的。 这很重要，因为这意味着你可以配置一个 JmsTemplate 的单例，然后把这个 共享的 引用安全的注入多个协作的对象中。 
	要清楚一点，JmsTemplate 是有状态的，因为它维护了 ConnectionFactory 的引用，但这个状态不是会话状态。 
	Spring提供了一个 ConnectionFactory 接口的实现，SingleConnectionFactory，它将在所有的 createConnection 调用中返回一个相同的 Connection，并忽略所有对 close的调用。
	这在测试和独立环境中相当有用，因为多个 JmsTemplate 调用可以使用同一个连接以跨越多个事务。SingleConnectionFactory 通常引用一个来自JNDI的标准 ConnectionFactory。 
	
	SessionCallback 和 ProducerCallback
	虽然send操作适用于许多常见的使用场景，但是有时你需要在一个JMS Session 或者 MessageProducer 上执行多个操作。
	接口 SessionCallback 和 ProducerCallback 分别提供了JMS Session 和 Session / MessageProducer 对。JmsTemplate 上的 execute() 方法执行这些回调方法。
	
	同步接收
	虽然JMS一般都和异步处理相关，但它也可以同步的方式使用消息。可重载的 receive(..) 方法提供了这种功能。
	在同步接收中，接收线程被阻塞直至获得一个消息，有可能出现线程被无限阻塞的危险情况。属性 receiveTimeout 指定了接收器可等待消息的延时时间。 

	MessageListenerAdapter类实现了MessageListener接口和SessionAwareMessageListener接口，
	它的主要作用是将接收到的消息进行类型转换，然后通过反射的形式把它交给一个普通的Java类进行处理。
    TextMessage转换为String对象； BytesMessage转换为byte数组；
    MapMessage转换为Map对象；ObjectMessage转换为对应的Serializable对象
    
	目标处理器是一个MessageListener或者是一个SessionAwareMessageListener的时候Spring将直接利用接收到的Message对象作为方法参数调用它们的onMessage方法。
	但是如果指定的目标处理器是一个普通的Java类时Spring将利用Message进行了类型转换之后的对象作为参数通过反射去调用真正的目标处理器的处理方法，
	这时通过MessageListenerAdapter的defaultListenerMethod属性来决定的，当我们没有指定该属性时，Spring会默认调用目标处理器的handleMessage方法。
    
    MessageListenerAdapter除了会自动的把一个普通Java类当做MessageListener来处理接收到的消息之外，其另外一个主要的功能是可以自动的发送返回消息。
	当我们用于处理接收到的消息的方法的返回值不为空的时候，Spring会自动将它封装为一个JMS Message，然后自动进行回复。
	回复消息将发送的目的地有这主要有两种方式可以指定：
          第一，可以通过发送的Message的setJMSReplyTo方法指定该消息对应的回复消息的目的地。
          生产者发送消息被MessageListenerAdapter处理之后，MessageListenerAdapter确实把监听器的返回内容封装成一个Message往原Message
          通过setJMSReplyTo方法指定的回复目的地发送了一个消息。对于MessageListenerAdapter对应的监听器处理方法返回的是一个null值或者返回类型是void的情况，
    MessageListenerAdapter是不会自动进行消息的回复的。
          第二，通过MessageListenerAdapter的defaultResponseDestination属性来指定。
         
         
    MessageConverter的作用主要有两方面，一方面它可以把我们的非标准化Message对象转换成我们的目标Message对象，这主要是用在发送消息的时候；
          另一方面它又可以把我们的Message对象转换成对应的目标对象，这主要是用在接收消息的时候。
          
          使用MessageListenerAdapter时，在对其进行初始化也就是调用其构造方法时，它会默认new一个Spring已经为我们实现了的MessageConverter——SimpleMessageConverter
          作为其默认的MessageConverter，这也就是为什么我们在使用MessageListenerAdapter的时候不需要指定MessageConverter但是消息还是会转换成对应的Java对象的原因。
          所以默认情况下我们使用MessageListenerAdapter时其对应的MessageListener的处理器方法参数类型必须是一个普通Java对象，而不能是对应的Jms Message对象。

          那如果我们在处理Jms Message的时候想使用MessageListenerAdapter，然后又希望处理最原始的Message，而不是经过MessageConverter进行转换后的Message该怎么办呢？
          这个时候我们只需要在定义MessageListenerAdapter的时候指定其MessageConverter为空就可以了。
	
	Spring在初始化JmsTemplate的时候也指定了其对应的MessageConverter为一个SimpleMessageConverter，所以如果我们平常没有什么特殊要求的时候可以
	直接使用JmsTemplate的convertAndSend系列方法进行消息发送，而不必繁琐的在调用send方法时自己new一个MessageCreator进行相应Message的创建。
	
	
	
	Spring提供了一个JmsTransactionManager用于对JMS ConnectionFactory做事务管理。这将允许JMS应用利用Spring的事务管理特性。
	JmsTransactionManager在执行本地资源事务管理时将从指定的ConnectionFactory绑定一个ConnectionFactory/Session这样的配对到线程中。
	JmsTemplate会自动检测这样的事务资源，并对它们进行相应操作。
	在Java EE环境中，ConnectionFactory会池化Connection和Session，这样这些资源将会在整个事务中被有效地重复利用。
	在一个独立的环境中，使用Spring的SingleConnectionFactory时所有的事务将公用一个Connection，但是每个事务将保留自己独立的Session。
	JmsTemplate可以利用JtaTransactionManager和能够进行分布式的 JMS ConnectionFactory处理分布式事务。

*/