package com.cloud.hystrixdashboard.client.impl;

import com.cloud.hystrixdashboard.client.ProducerClient;
import org.springframework.stereotype.Component;

@Component
public class ProducerClientImpl implements ProducerClient {
    @Override
    public String getProducer() {
        System.out.println("feign-producer 一异常");
        return "feign-consumer 一异常";
    }

    @Override
    public String getProducerName(String name) {
        System.out.println("feign-producer 一异常");
        return "feign-consumer 一异常";
    }
}
