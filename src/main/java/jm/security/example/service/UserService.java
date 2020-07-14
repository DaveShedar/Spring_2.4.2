package jm.security.example.service;

import jm.security.example.model.User;

import java.util.List;

public interface UserService {
        User getUser(String login);

    public List< User > getUsers();

    public void saveUser(User user);

    public User getUser(Long theId);

    public void deleteUser(Long theId);
    }

