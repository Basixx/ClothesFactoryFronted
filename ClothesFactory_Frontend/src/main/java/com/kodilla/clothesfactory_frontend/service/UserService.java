package com.kodilla.clothesfactory_frontend.service;

import com.kodilla.clothesfactory_frontend.domain.User;
import java.util.List;
import java.awt.*;
import java.util.ArrayList;

public class UserService {

    private List<User> users;
    private static UserService userService;

    private UserService(List<User> users) {
//        this.users = users;
        this.users = exampleData();
    }

    public static UserService getInstance() {

        if (userService == null) {
            userService = new UserService(new ArrayList<User>());
        }
        return userService;
    }

    public List<User> getUsers() {
        return new ArrayList<>(users);
    }

    private List<User> exampleData() {
        List<User> users = new ArrayList<>();
        users.add(new User("John", "Smith", "111111", "john@smith.com", "password"));
        return users;
    }

    public void save(User user) {
        this.users.add(user);
    }

    public void delete (User user) {
        this.users.remove(user);
    }
}
