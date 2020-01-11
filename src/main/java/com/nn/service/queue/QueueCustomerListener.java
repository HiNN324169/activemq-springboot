package com.nn.service.queue;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Component
public class QueueCustomerListener {

    @JmsListener(destination = "${queue.name}")
    public void receive(TextMessage textMessage) throws JMSException {
        System.out.println("消费者接收到的消息："+ textMessage.getText());
    }
}
