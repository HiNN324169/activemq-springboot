package com.nn;

import com.nn.controller.TestController;
import com.nn.service.queue.QueueProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    QueueProducer queueProducer;

    @Autowired
    TestController testController;

    @Test
    void contextLoads() {
        queueProducer.produceMsg();
    }

    @Test
    void helloTest(){
        System.out.println(testController.hello());
    }

}
