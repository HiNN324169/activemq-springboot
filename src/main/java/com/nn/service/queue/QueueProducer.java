package com.nn.service.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component(value = "queueProducer")
public class QueueProducer {

    private String msg;
    public static final String SEND_SUCCESS="1";
    public static final String SEND_FAIL="0";

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;



    @SuppressWarnings("deprecation")
    public String produceMsg(){
        try {
//            jmsMessagingTemplate.convertAndSend(queue,msg+new Date().toLocaleString());
            JmsTemplate jmsTemplate = jmsMessagingTemplate.getJmsTemplate();
            jmsTemplate.setDefaultDestination(queue);
            jmsTemplate.send(new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    TextMessage textMessage = session.createTextMessage(msg);
                    return textMessage;
                }
            });
            return SEND_SUCCESS;
        }catch (Exception e){
            return SEND_FAIL;
        }
    }

    /**
     *  实现 延迟 定时发送消息
     *
     */
//    @SuppressWarnings("deprecation")
//    @Scheduled(fixedDelay = 2000)
//    public void producerMsgScheduled(){
//        jmsMessagingTemplate.convertAndSend(queue,msg+"   Scheduled："+ new Date().toLocaleString());
//        System.out.println("消息发送成功...");
//    }
}
