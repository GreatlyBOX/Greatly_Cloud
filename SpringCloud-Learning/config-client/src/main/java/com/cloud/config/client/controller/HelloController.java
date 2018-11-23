package com.cloud.config.client.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RefreshScope //开启刷新
@RestController
public class HelloController {
    @Value("${ftp.url}")
    String name;
    @RequestMapping("/hello")
    public String hrllo(){
        return name;
    }

}
