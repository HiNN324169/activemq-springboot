package com.nn.service.queue;

import com.sun.media.sound.SoftTuning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.util.Date;

@Component
public class QueueProducer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    @SuppressWarnings("deprecation")
    public void produceMsg(){
        jmsMessagingTemplate.convertAndSend(queue,"message:"+new Date().toLocaleString());
    }

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
}
