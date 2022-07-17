package ru.kata.spring.boot_security.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.model.User;


public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
