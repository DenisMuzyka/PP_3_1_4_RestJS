package ru.kata.spring.boot_security.bootstrap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.bootstrap.model.Role;
import ru.kata.spring.boot_security.bootstrap.model.User;
import ru.kata.spring.boot_security.bootstrap.service.UserServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@Transactional
public class HelloController {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public HelloController(UserServiceImpl userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String startPage() {
        List<User> users = userService.getAllUsers();

        if (users.isEmpty()) {
            Role admin = new Role("ROLE_ADMIN");
            Role user = new Role("ROLE_USER");
            Set<Role> adminRole = new HashSet<>();
            Set<Role> userRole = new HashSet<>();
            Set<Role> anyRole = new HashSet<>();
            adminRole.add(admin);
            userRole.add(user);
            anyRole.add(admin);
            anyRole.add(user);
            userService.addUser(new User
                    ("admin","admin",30,"admin30@gmail.com","admin30@gmail.com", adminRole));
            userService.addUser(new User
                    ("user","user",20,"user20@gmail.com","user20@gmail.com", userRole));
            userService.addUser(new User
                    ("user2","user2",22,"user222@gmail.com","user222@gmail.com", userRole));
            userService.addUser(new User
                    ("any","any",27,"any27@gmail.com","any27@gmail.com", anyRole));
            userService.addUser(new User
                    ("any2","any2",30,"any230@gmail.com", "any230@gmail.com", anyRole));
        }
        return "redirect:/login";
    }
}
