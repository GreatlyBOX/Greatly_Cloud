package com.logstash.clouldlogstash;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {
    private final static Logger logger = LoggerFactory.getLogger(ClouldLogstashApplication.class);
    @RequestMapping("test")
    public @ResponseBody String test(){
        logger.info("测试日志");
        return "s";
    }
}
