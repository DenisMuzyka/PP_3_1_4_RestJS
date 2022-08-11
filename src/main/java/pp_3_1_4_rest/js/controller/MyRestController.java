package pp_3_1_4_rest.js.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pp_3_1_4_rest.js.model.Role;
import pp_3_1_4_rest.js.model.User;
import pp_3_1_4_rest.js.service.RoleServiceImpl;
import pp_3_1_4_rest.js.service.UserServiceImpl;

import java.security.Principal;
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

    @GetMapping("/roles")
    public List<Role> getAllRoles(){
        return roleService.findAllRoles();
    }

    @GetMapping("/userinfo")
    public User getUser(Principal principal) {
        return userService.findByEmail(principal.getName());
    }

    @PostMapping("/users")
    public User addNewUser(@RequestBody User user) {
        userService.addUser(user);
        return user;
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable long id) {
        userService.removeUser(id);
        return "User with ID = " + id + " was deleted";
    }
}

