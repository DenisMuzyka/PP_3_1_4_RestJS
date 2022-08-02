package pp_3_1_4_rest.js.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pp_3_1_4_rest.js.dao.UserRepo;
import pp_3_1_4_rest.js.dao.RoleRepo;
import pp_3_1_4_rest.js.model.User;

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

