package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserDetailsServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class HelloController {

    private final UserDetailsServiceImpl userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public HelloController(UserDetailsServiceImpl userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String startPage() {
        List<User> users = userDetailsService.getAllUsers();

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
            userDetailsService.addUser(new User
                    ("admin",
                            passwordEncoder.encode("admin"),
                            "Admin", (byte) 30, "admin30@gmail.com", adminRole));
            userDetailsService.addUser(new User
                    ("user",
                            passwordEncoder.encode("user"),
                            "User", (byte) 20, "user20@gmail.com", userRole));
            userDetailsService.addUser(new User
                    ("user2",
                            passwordEncoder.encode("user2"),
                            "User2", (byte) 22, "user222@gmail.com", userRole));
            userDetailsService.addUser(new User
                    ("any",
                            passwordEncoder.encode("any"),
                            "Any", (byte) 27, "any27@gmail.com", anyRole));
            userDetailsService.addUser(new User
                    ("any2",
                            passwordEncoder.encode("any2"),
                            "Any2", (byte) 30, "any230@gmail.com", anyRole));
        }
        return "startPage";
    }
}
