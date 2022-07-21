package ru.kata.spring.boot_security.bootstrap.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.bootstrap.model.User;


public interface UserRepo extends JpaRepository<User, Long> {
    UserDetails findUserByEmail(String email);
    User findByEmail(String email);
}
