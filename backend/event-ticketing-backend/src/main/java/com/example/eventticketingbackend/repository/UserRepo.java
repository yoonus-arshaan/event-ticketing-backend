package com.example.eventticketingbackend.repository;

import com.example.eventticketingbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {


}
