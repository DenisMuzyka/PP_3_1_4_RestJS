package ru.kata.spring.boot_security.bootstrap.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.bootstrap.model.Role;
import ru.kata.spring.boot_security.bootstrap.model.User;
import ru.kata.spring.boot_security.bootstrap.service.RoleService;
import ru.kata.spring.boot_security.bootstrap.service.UserServiceImpl;

import java.security.Principal;
import java.util.List;


@Controller
public class AdminController {

    private final UserServiceImpl userDetailServiceImpl;
    private final RoleService roleService;
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AdminController(UserServiceImpl userDetailServiceImpl, RoleService roleService, PasswordEncoder bCryptPasswordEncoder) {
        this.userDetailServiceImpl = userDetailServiceImpl;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping(value = "/admin")
    public String openStartPage(Model model, Principal principal) {
        List<User> users = userDetailServiceImpl.getAllUsers();
        List<Role> roles = roleService.findAllRoles();
        User user = userDetailServiceImpl.findByEmail(principal.getName());
        model.addAttribute("users", users);
        model.addAttribute("meUser", user);
        model.addAttribute("roles", roles);
        return "/admin";
    }

    @GetMapping("/admin/users/add")
    public String addUser(Model model, Principal principal) {
        List<Role> roles = roleService.findAllRoles();
        User user = userDetailServiceImpl.findByEmail(principal.getName());
        model.addAttribute("roles", roles);
        model.addAttribute("user", new User());
        model.addAttribute("meUser", user);
        return "addUser";
    }

    @PostMapping("/admin")
    public String createUser(@ModelAttribute("user") User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDetailServiceImpl.addUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String removeUser(@PathVariable("id") Long id) {
        userDetailServiceImpl.removeUser(id);
        return "redirect:/admin";
    }

    @PatchMapping("/admin/edit/{id}")
    public String editUser (@ModelAttribute("editUser") User user,Model model, @PathVariable("id") Long id) {
        model.addAttribute("editUser", userDetailServiceImpl.findUserById(id));
        userDetailServiceImpl.updateUser(user, id);
        return "redirect:/admin";
    }
}
