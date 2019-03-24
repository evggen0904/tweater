package com.spring.example.tweater.repository;

import com.spring.example.tweater.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    User findUserByUsername(String userName);

    User findByActivationCode(String code);
}
