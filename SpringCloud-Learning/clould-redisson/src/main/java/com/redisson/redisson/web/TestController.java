package com.redisson.redisson.web;

import com.redisson.redisson.utils.DateUtils;
import com.redisson.redisson.utils.RedisLockUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.TimeUnit;


/**
 * @author 启哲
 * @创建时间2020/9/8
 * @知乎专栏 Spring Boot学习教程
 * https://zhuanlan.zhihu.com/c_152148543
 */
@RestController
@Slf4j
public class TestController {


    @RequestMapping("test")
    public void test() {
        String lockKey = "lock-test";
        try {
            log.info("开始进入加锁,Time:"+ DateUtils.format(new Date()));
            // 加锁，设置超时时间2s
            RedisLockUtil.lock(lockKey,5, TimeUnit.SECONDS);
            log.info("模拟执行业务");
            log.info("结束加锁,Time:"+ DateUtils.format(new Date()));
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }finally {
            // 释放锁
            RedisLockUtil.unlock(lockKey);
        }
    }
}
