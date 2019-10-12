package com.cloud.gatewaystomp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StompController {

    private static final Logger logger=LoggerFactory.getLogger(StompController.class);

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat")
    //SendTo 发送至 Broker 下的指定订阅路径
    @SendTo("/toAll/bulletScreen")
    public String say(StompMessageDTO clientMessage) {
        //方法用于广播测试
        if (clientMessage!=null){
            if (clientMessage.getMessage()!=null){
                clientMessage.setMessage(clientMessage.getMessage().trim());
            }
        }
        logger.info(clientMessage.getUsername()+":"+clientMessage.getMessage());
        return clientMessage.getMessage();
    }


    /**
     * 用户在前端订阅的消息队列名称为"/toAll/username"
     * 通过teleWebSocketManager发送信息给指定用户
     */
    @ResponseBody
    @RequestMapping(value="/sendToOne")
    public String sendToOne(String username, String message) {
//        messagingTemplate.send("/toAll/" + username, msg);
        messagingTemplate.convertAndSend("/toAll/bulletScreen", "测试发送");
        return "成功";
    }
}