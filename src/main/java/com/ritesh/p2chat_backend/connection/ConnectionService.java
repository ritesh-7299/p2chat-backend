package com.ritesh.p2chat_backend.connection;

import com.ritesh.p2chat_backend.user.User;
import com.ritesh.p2chat_backend.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConnectionService {
    private final ConnectionRepository repository;
    private final UserService userService;

    /**
     * Function to create a connection between two users
     *
     * @param fromUserId
     * @param toUserId
     * @return
     */
    public boolean createConnection(String fromUserId, String toUserId) {
        try {
            User fromUser = userService.getUserByUserId(fromUserId);
            User toUser = userService.getUserByUserId(toUserId);
            Connection connection = new Connection();
            connection.setFromUser(fromUser);
            connection.setToUser(toUser);
            repository.save(connection);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
