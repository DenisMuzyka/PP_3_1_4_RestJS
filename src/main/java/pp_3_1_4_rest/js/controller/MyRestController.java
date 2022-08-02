package pp_3_1_4_rest.js.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pp_3_1_4_rest.js.model.User;
import pp_3_1_4_rest.js.service.RoleServiceImpl;
import pp_3_1_4_rest.js.service.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {

    private RoleServiceImpl roleService;
    private UserServiceImpl userService;

    @Autowired
    public MyRestController(RoleServiceImpl roleService, UserServiceImpl userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable long id) {
        return userService.findUserById(id);
    }

    @PostMapping("/users")
    public User addNewUser(@RequestBody User user) {
        userService.addUser(user);
        return user;
    }

    @PutMapping("/users/{id}")
    public User updateUser(@RequestBody User user, @PathVariable long id) {
        userService.updateUser(user, id);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable long id) {
        userService.removeUser(id);
        return "User with ID = " + id + " was deleted";
    }
}

