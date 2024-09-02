package com.lcwd.user.service.services;

import com.lcwd.user.service.entities.User;

import java.util.List;

public interface UserService {
    //create
    User saveUser(User user);
    //get all the users
    List<User> getAllUser();
    //get single User
    User getUser(String userId);
    //update user
    //User updateuser(User user);
    //delete user
    //Void deleteUser(String userId);

}
