package com.nn.borker;

import org.apache.activemq.broker.BrokerService;

public class MyBroker {

    public static void main(String[] args) throws Exception {
        BrokerService brokerService = new BrokerService();
        brokerService.setUseJmx(true);
        brokerService.addConnector("tcp://127.0.0.1:61616");
        brokerService.start();
        System.out.println("broker 服务启动成功...");
    }
}
