package com.example.eventticketingbackend.service;

import com.example.eventticketingbackend.exception.UserNotFoundException;
import com.example.eventticketingbackend.model.User;
import com.example.eventticketingbackend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User addUser(User user) {
        return userRepo.save(user);
    }

    public User findUserById(Integer id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id: " + id + " not found"));
    }
}
