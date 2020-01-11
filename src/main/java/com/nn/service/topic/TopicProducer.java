package com.nn.service.topic;

import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.util.Date;

@Component(value = "topicProducer")
public class TopicProducer {

    public static final String SEND_SUCCESS="1";
    public static final String SEND_FAIL="0";

    @Autowired
    private Topic topic;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    private String msg;
    public void setTopicMeg(String msg){
        this.msg=msg;
    }

    @SuppressWarnings("deprecation")
    public String sendTopic(){
        try {
//            jmsMessagingTemplate.convertAndSend(topic,msg + new Date().toLocaleString());
            JmsTemplate jmsTemplate = jmsMessagingTemplate.getJmsTemplate();
            jmsTemplate.setPubSubDomain(true);
            jmsTemplate.setDefaultDestination(topic);
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
}
