package com.kodilla.clothesfactory_frontend.configuration.client;

import com.kodilla.clothesfactory_frontend.domain.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UserClient {

    private final RestTemplate restTemplate;
    private static UserClient userClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserClient.class);
    private String url = "http://localhost:8080/v1/users";

    public static UserClient getInstance() {
        if (userClient == null) {
            userClient = new UserClient(new RestTemplate());
        }
        return userClient;
    }

    public List<User> getAllUsers() {
        try {
            User[] usersResponse = restTemplate.getForObject(
                    url,
                    User[].class
            );
            return Optional.ofNullable(usersResponse)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public List<User> getUser(int userId) {
        try {
            User[] usersResponse = restTemplate.getForObject(
                    url + "/" + userId,
                    User[].class
            );
            return Optional.ofNullable(usersResponse)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public User createUser(User newUser) {
        return restTemplate.postForObject(
                url,
                newUser,
                User.class
        );
    }

    public void updateUser(int userId, User updatedUser) {
        restTemplate.put(
                url + "/ " + userId,
                updatedUser
        );
    }

    public void deleteUser(int userId) {
        restTemplate.delete(url + "/" + userId);
    }
}
