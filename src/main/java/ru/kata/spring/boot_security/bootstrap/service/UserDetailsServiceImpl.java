package ru.kata.spring.boot_security.bootstrap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.bootstrap.dao.RoleRepo;
import ru.kata.spring.boot_security.bootstrap.dao.UserRepo;
import ru.kata.spring.boot_security.bootstrap.model.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepo userRepo;
    private RoleRepo roleRepo;

    @Autowired
    public UserDetailsServiceImpl (UserRepo userRepo, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        user.setRoles(roleRepo.findAllByUsersId(user.getId()));
        return user;

    }
}

