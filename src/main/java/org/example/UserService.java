package org.example;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void createUser(String name){
        userDao.createUser(new User(null,name));
    }

    public void createUsers(List<String> names){
        userDao.createUsers(names);
    }

    public void deleteUser(Long id){
        userDao.deleteUser(id);
    }
    public void deleteUserByName(String name){
        userDao.deleteUserByName(name);
    }

    public void deleteAllUsers(){
        userDao.deleteAllUsers();
    }

    public User getUser(Long id){
        return userDao.getUser(id);
    }

    public User getUserByName(String name){
        return userDao.getUserByName(name);
    }
    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }
}
