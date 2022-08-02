package pp_3_1_4_rest.js.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pp_3_1_4_rest.js.model.Role;
import pp_3_1_4_rest.js.model.User;
import pp_3_1_4_rest.js.service.RoleServiceImpl;
import pp_3_1_4_rest.js.service.UserServiceImpl;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class MyPostConstruct {

    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;

    @Autowired
    public MyPostConstruct(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void loadTable() {
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
            roleService.saveRole(admin);
            roleService.saveRole(user);
            userService.addUser(new User
                    ("admin", "admin", 30, "admin30@gmail.com", "admin30@gmail.com", adminRole));
            userService.addUser(new User
                    ("user", "user", 20, "user20@gmail.com", "user20@gmail.com", userRole));
            userService.addUser(new User
                    ("user2", "user2", 22, "user222@gmail.com", "user222@gmail.com", userRole));
            userService.addUser(new User
                    ("any", "any", 27, "any27@gmail.com", "any27@gmail.com", anyRole));
            userService.addUser(new User
                    ("any2", "any2", 30, "any230@gmail.com", "any230@gmail.com", anyRole));
        }
    }
}
