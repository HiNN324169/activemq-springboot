package com.nn.controller;

import com.nn.service.queue.QueueProducer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class QueueController {

    @Resource(name = "queueProducer")
    private QueueProducer queueProducer;

    @RequestMapping("/queue/{msg}")
    public Object sendQueue(@PathVariable("msg") String msg) {
        queueProducer.setMsg(msg);
        return queueProducer.produceMsg();
    }
}
