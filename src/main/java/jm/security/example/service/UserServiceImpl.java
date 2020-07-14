package jm.security.example.service;

import jm.security.example.dao.UserDao;
import jm.security.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUser(String login) {
        return userDao.getUserByName(login);
    }

    @Override
    @Transactional
    public List< User > getUsers() {
        return userDao.getUsers();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    @Transactional
    public User getUser(Long theId) {
        return userDao.getUser(theId);
    }

    @Override
    @Transactional
    public void deleteUser(Long theId) {
        userDao.deleteUser(theId);
    }
}
