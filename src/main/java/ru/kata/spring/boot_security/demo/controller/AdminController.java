package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserDetailsServiceImpl;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserDetailsServiceImpl userDetailServiceImpl;
    private final RoleService roleService;
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AdminController(UserDetailsServiceImpl userDetailServiceImpl, RoleService roleService, PasswordEncoder bCryptPasswordEncoder) {
        this.userDetailServiceImpl = userDetailServiceImpl;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        List<Role> roles = roleService.findAllRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("user", new User());
        model.addAttribute("users", userDetailServiceImpl.getAllUsers());
        return "admin";
    }

    @PostMapping("/users/add")
    public String addUser(Model model) {
        User user = new User();
        List<Role> roles = roleService.findAllRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("user", new User());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDetailServiceImpl.addUser(user);

        return "redirect:/admin";
    }

    @GetMapping("/users/remove/{id}")
    public String removeUser(@PathVariable("id") Long id) {
        userDetailServiceImpl.removeUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/users/edit/{id}")
    public String editPage(@PathVariable("id") Long id, Model model) {
        List<Role> roles = roleService.findAllRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("user", userDetailServiceImpl.findUserById(id));
        return "edit";
    }
    @PatchMapping("/users/{id}")
    public String editUser (@ModelAttribute("user") User user) {
        userDetailServiceImpl.updateUser(user);
        return "redirect:/admin";
    }
}
