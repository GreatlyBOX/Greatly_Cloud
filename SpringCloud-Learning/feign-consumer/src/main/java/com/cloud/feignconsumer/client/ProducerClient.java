package com.cloud.feignconsumer.client;

import com.cloud.feignconsumer.client.impl.ProducerClientImpl;
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
