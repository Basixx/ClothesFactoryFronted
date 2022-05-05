package com.kodilla.clothesfactory_frontend.service;

import com.kodilla.clothesfactory_frontend.configuration.client.UserClient;
import com.kodilla.clothesfactory_frontend.domain.User;
import java.util.List;

public class UserService {


    private static UserService userService;
    private final UserClient userClient = UserClient.getInstance();



    public static UserService getInstance() {

        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public List<User> getAllUsers() {
        return userClient.getAllUsers();
    }

    public User createUser(User user) {
        return userClient.createUser(user);
    }

    public User authenticateUser(String email, String password) {
        return userClient.authenticateUser(email, password);
    }

    public User getUser(int userId) {
        return userClient.getUser(userId);
    }

    public void updateUser(int userId, User user) {
        userClient.updateUser(userId, user);
    }

    public void deleteUser(int userId) {
        userClient.deleteUser(userId);
    }
}
