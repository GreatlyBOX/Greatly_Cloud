package com.cloud.hystrixdashboard.client;

import com.cloud.hystrixdashboard.client.impl.ProducerClientImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "feign-producer",fallback= ProducerClientImpl.class)
public interface ProducerClient {
    @GetMapping( "/getProducer")
    String getProducer();
    @GetMapping("/getProducerName")
    String getProducerName(@RequestParam("name")String name);
}
