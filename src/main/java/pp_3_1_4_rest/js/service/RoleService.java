package pp_3_1_4_rest.js.service;

import pp_3_1_4_rest.js.model.Role;

import java.util.Collection;
import java.util.List;

public interface RoleService {
    Role findById(Long id);
    Role findByRole(String role);
    List<Role> findAllRoles();
    void saveRole(Role role);
    void saveAllRoles(Collection<Role> roles);
}
