package com.cloud.gatewaystomp;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
/**
 * 检查握手请求和响应, 对WebSocketHandler传递属性
 */
public class HandleShakeInterceptors implements HandshakeInterceptor {
    private static final Logger logger;

    static {
        logger = LoggerFactory.getLogger(HandleShakeInterceptors.class);
    }

    /**
     * 在握手之前执行该方法, 继续握手返回true, 中断握手返回false.
     * 通过attributes参数设置WebSocketSession的属性
     *
     * @param request
     * @param response
     * @param wsHandler
     * @param attributes
     * @return
     * @throws Exception
     */

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        String URI = request.getURI().toString();
        logger.info("当前session的URI={}",URI);
        //ServletServerHttpRequest serverHttpRequest = (ServletServerHttpRequest) request;
        //HttpSession session = serverHttpRequest.getServletRequest().getSession();
//        attributes.put("WEBSOCKET_USERID",ID);
        return true;
    }

    /**
     * 在握手之后执行该方法. 无论是否握手成功都指明了响应状态码和相应头.
     *
     * @param request
     * @param response
     * @param wsHandler
     * @param exception
     */
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception exception) {
        logger.info("进来webSocket的afterHandshake拦截器！");

    }

}
