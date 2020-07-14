package jm.security.example.dao;

import jm.security.example.model.User;

import java.util.List;

public interface UserDao {

    User getUserByName(String name);

    public List< User > getUsers();

    public void saveUser(User user);

    public User getUser(Long theId);

    public void deleteUser(Long theId);
}
