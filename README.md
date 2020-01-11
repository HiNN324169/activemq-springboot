# ActiveMQ + springboot 整合 

GitHub-URL：https://github.com/HiNN324169/activemq-springboot

版本说明
- springboot.varsion：2.2.2.RELEASE
- spring.version：5.2.2.RELEASE
- activemq.version：5.15.11
- java.version：1.8
- maven.version：3.3.9
- IDEA：2019.3.1

### 一、创建springboot项目

### 二、引入所需依赖
- 见 pom.xml

---
## 【queue】
### 三、创建 application-dev.yml 配置文件
```$xslt
server:
  port: 8080 # 配置服务器端口
  servlet:
    context-path: /activemq-springboot # 项目名称

spring:
  activemq: # 配置activemq
    broker-url: tcp://127.0.0.1:61616
    user: admin
    password: admin
    pool:
      max-connections: 100
  jms:
    pub-sub-domain: false # false 代表队列，true 主题；

queue.name: boot-queue-name # 自定义一个队列名称（取数据：@Value("${queue.name}")）
```

### 四、创建 队列配置类
```$xslt
@Component
public class QueueConfig {

    @Value("${queue.name}")
    private String queueName;

    @Bean
    public Queue queue(){
        return new ActiveMQQueue(queueName);
    }
}
```

### 五、创建生产者
```$xslt
@Component
public class QueueProducer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    @SuppressWarnings("deprecation") // 去除警告
    public void produceMsg(){
        // 发送消息
        jmsMessagingTemplate.convertAndSend(queue,"message:"+new Date().toLocaleString());
    }
}

```



### 六、创建消费者（监听器）

- 使用监听器注解：
```$xslt
@JmsListener(destination = "${queue.name}")
```

```$xslt
@Component
public class QueueCustomer {

    @JmsListener(destination = "${queue.name}")
    public void receive(TextMessage textMessage) throws JMSException {
        System.out.println("消费者接收到的消息："+ textMessage.getText());
    }
}
```

### 七、在 springboot 启动类 上 添加 启动jms 注解
```$xslt
// 启动 jms
@EnableJms
```


### 八、测试
- 本项目使用了 内嵌的 activemq broker 
- springboot 使用 内嵌broker 需要引入kahadb依赖
```$xslt
<dependency>
    <groupId>org.apache.activemq</groupId>
    <artifactId>activemq-kahadb-store</artifactId>
</dependency>
```
- 启动 MyBroker
```$xslt
public class MyBroker {

    public static void main(String[] args) throws Exception {
        BrokerService brokerService = new BrokerService();
        brokerService.setUseJmx(true);
        brokerService.addConnector("tcp://127.0.0.1:61616");
        brokerService.start();
        System.out.println("broker 服务启动成功...");
    }
}
```
    
- 调用 生产者 发送消息
    - 本次测试用了 测试类进行测试，引入测试依赖如下：
```$xslt
<!--        junit 5 测试包-->
<dependency>
    <groupId>org.junit.platform</groupId>
    <artifactId>junit-platform-launcher</artifactId>
    <scope>test</scope>
</dependency>
<!--        junit 5 测试包-->
```
启动测试类：com.nn.DemoApplicationTests.contextLoads()

- 消费者 监听器 自动接收消息

### 九、实现 定时发送消息

#### 九-1、生产者 定时发送消息
- 使用注解：@Scheduled
- 参数：fixedDelay 延迟时间：毫秒
````$xslt
/**
     *  实现 延迟 定时发送消息
     *
     */
    @SuppressWarnings("deprecation")
    @Scheduled(fixedDelay = 2000)
    public void producerMsgScheduled(){
        jmsMessagingTemplate.convertAndSend(queue,"Scheduled："+ new Date().toLocaleString());
        System.out.println("消息发送成功...");
    }
````
#### 九-2、在 springboot 启动类上启用定时器
```$xslt
// 启用 定时发送
@EnableScheduling
```

#### 九-3、启动springboot
- 生产者 每隔两秒发送消息
- 定时器 时刻监听消息 并获取消息

---
## 【Topic】

- 修改参数
```angular2
  jms:
    pub-sub-domain: true # false 代表队列，true 主题；
```








