package com.ritesh.p2chat_backend.config;

import com.ritesh.p2chat_backend.connection.ConnectionService;
import com.ritesh.p2chat_backend.socket.SignalingHandler;
import com.ritesh.p2chat_backend.socket.interceptor.CustomHandshakeInterceptor;
import com.ritesh.p2chat_backend.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
@Slf4j
public class WebsocketConfig implements WebSocketConfigurer {
    private final ConnectionService connectionService;
    private final UserService userService;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        log.info("Socket handler set");
        registry.addHandler(new SignalingHandler(connectionService, userService), "/signal")
                .addInterceptors(new CustomHandshakeInterceptor())
                .setAllowedOrigins("*");
    }
}
