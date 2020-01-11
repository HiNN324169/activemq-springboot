package com.nn.controller;

import com.nn.service.topic.TopicProducer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TopicController {

    @Resource(name = "topicProducer")
    private TopicProducer topicProducer;

    @RequestMapping(value = "/topic/{msg}")
    public Object sendTopic(@PathVariable("msg") String msg){
        topicProducer.setTopicMeg(msg);
        return topicProducer.sendTopic();
    }
}
