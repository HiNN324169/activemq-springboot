package com.nn.service.topic;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Component
public class TopicConsumerListener {

    @JmsListener(destination = "${topic.name}")
    public void topicReceive(TextMessage textMessage) throws JMSException {
        System.out.println("topic: 收到的主题消息为: "+ textMessage.getText());
    }
}
