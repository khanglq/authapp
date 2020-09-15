package com.khanglq.authapp.service;

import com.khanglq.authapp.model.entity.Action;
import com.khanglq.authapp.model.entity.User;
import com.khanglq.authapp.repo.ActionRepo;
import com.khanglq.authapp.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by khanglq on 31/8/2020.
 */

@Service
public class UserService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> getAll() {
        List<User> userList = userRepo.findAll();
        if(userList != null) return userList;
        else {
            System.out.println("No user found");
            return null;
        }
    }

    public List<User> getByUsername(String username) {
        List<User> userList = userRepo.findByUsername(username);
        if(userList != null) return userList;
        else {
            System.out.println("No user found");
            return null;
        }
    }

}
