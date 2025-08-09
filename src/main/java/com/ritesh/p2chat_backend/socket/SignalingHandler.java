package com.ritesh.p2chat_backend.socket;

import com.ritesh.p2chat_backend.connection.ConnectionService;
import com.ritesh.p2chat_backend.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Slf4j
@Component
public class SignalingHandler extends TextWebSocketHandler {
    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
    private final ConnectionService connectionService;
    private final UserService userService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String userId = session.getAttributes().get("localId").toString(); // Replace with custom ID if needed
        sessions.put(userId, session);
        userService.createUser(userId);
        System.out.println("Connection success----->" + userId);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        JSONObject json = new JSONObject(message.getPayload());
        String to = json.getString("to");
        System.out.println("Message received to " + to + " message:" + message.getPayload());
        System.out.println("-------------------------------------------------");
        if (json.getString("type").equals("connection")) {
            System.out.println("need to create new connection");
            connectionService.createConnection(json.getString("from"), json.getString("to"));
        }
        WebSocketSession targetSession = sessions.get(to);
        if (targetSession != null && targetSession.isOpen()) {
            targetSession.sendMessage(new TextMessage(message.getPayload()));
            System.out.println("data send to user:" + to);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        log.info("Disconnection happned**************************************");
        String userId = session.getAttributes().get("localId").toString();
        sessions.remove(userId);
        userService.deleteUser(userId);
    }
}
