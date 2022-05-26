package com.kodilla.clothesfactory_frontend.service;

import com.kodilla.clothesfactory_frontend.configuration.client.UsersClient;
import com.kodilla.clothesfactory_frontend.domain.User;
import org.springframework.web.client.RestClientException;
import java.util.List;

public class UserService {

    private static UserService userService;
    private final UsersClient usersClient = UsersClient.getInstance();

    public static UserService getInstance() {

        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public List<User> getAllUsers() {
        return usersClient.getAllUsers();
    }

    public User createUser(User user) {
        return usersClient.createUser(user);

    }

    public User authenticateUser(String email, String password) {
        return usersClient.authenticateUser(email, password);
    }

    public User getUser(int userId) {
        return usersClient.getUser(userId);
    }

    public void updateUser(int userId, User user) {
        usersClient.updateUser(userId, user);
    }

    public void deleteUser(int userId) {
        usersClient.deleteUser(userId);
    }
}