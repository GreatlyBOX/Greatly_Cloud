package com.cloud.feignconsumer.controller;

import com.cloud.feignconsumer.client.ProducerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Autowired
    ProducerClient producerClient;
    @GetMapping(value = "/getProducer")
    public String getProducer(){
        return producerClient.getProducer();
    }
    @GetMapping("/getProducerName")
    public String getProducerName(@RequestParam("name") String name){
        System.out.println("调用传参"+name);
        return producerClient.getProducerName(name);
    }
}
