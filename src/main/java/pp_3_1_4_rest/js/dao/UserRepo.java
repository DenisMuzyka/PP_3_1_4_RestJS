package pp_3_1_4_rest.js.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import pp_3_1_4_rest.js.model.User;


public interface UserRepo extends JpaRepository<User, Long> {
    UserDetails findUserByEmail(String email);
    User findByEmail(String email);
}
