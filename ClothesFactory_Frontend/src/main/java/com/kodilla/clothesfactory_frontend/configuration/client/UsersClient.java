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
public class UsersClient {

    private final RestTemplate restTemplate;
    private static UsersClient usersClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersClient.class);
    private final String url = "http://localhost:8080/v1/users";

    public static UsersClient getInstance() {
        if (usersClient == null) {
            usersClient = new UsersClient(new RestTemplate());
        }
        return usersClient;
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

    public User getUser(int userId) {
        try {
            return restTemplate.getForObject(
                    url + "/" + userId,
                    User.class
            );
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public User authenticateUser(String email, String password) {
        try {
            return restTemplate.getForObject(
                    url + "/" + email + "/" + password,
                    User.class
            );
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }
    }

    public User createUser(User newUser) {
        try{
            return restTemplate.postForObject(
                    url,
                    newUser,
                    User.class
            );
        } catch (RestClientException e) {
            throw e;
        }

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