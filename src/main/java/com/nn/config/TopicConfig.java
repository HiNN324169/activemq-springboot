package com.nn.config;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.jms.Topic;

@Component
public class TopicConfig {

    @Value("${topic.name}")
    private String topic_name;

    @Bean
    public Topic topic() {
        return new ActiveMQTopic(topic_name);
    }
}
