package com.cloud.feign.producer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    @GetMapping("/getProducer")
    public String getProducer(){
        System.out.println("hello,this is Producer！");
        return "hello,this is Producer！";
    }
    @GetMapping("/getProducerName")
    public String getProducerName(@RequestParam("name") String name){
        System.out.println("hello,this is Producer！param="+name);
        return "hello,this is Producer！param="+name;
    }
}
