package pp_3_1_4_rest.js.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pp_3_1_4_rest.js.model.Role;

import java.util.Collection;


public interface RoleRepo extends JpaRepository<Role, Long> {

    Role findByRole(String role);
    Collection<Role> findAllByUsersId(long id);
}
