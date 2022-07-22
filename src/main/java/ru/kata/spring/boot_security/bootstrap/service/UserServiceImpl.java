package ru.kata.spring.boot_security.bootstrap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.bootstrap.dao.UserRepo;
import ru.kata.spring.boot_security.bootstrap.model.User;

import java.util.List;


@Service
public class UserServiceImpl {

    private final UserRepo userRepo;
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, PasswordEncoder bCryptPasswordEncoder) {
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    public void addUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    @Transactional
    public void updateUser(User user, Long id) {
        if (user.getPassword() == null ||
                user.getPassword().equals("") || user.getPassword().equals(findUserById(id).getPassword())) {
            user.setPassword(findUserById(id).getPassword());
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        userRepo.save(user);
    }

    @Transactional
    public void removeUser(Long id) {
        userRepo.deleteById(id);
    }

    @Transactional
    public List<User> getAllUsers() {
        return userRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Transactional
    public User findUserById(long id) {
        return userRepo.findById(id).get();
    }

    @Transactional
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

}
