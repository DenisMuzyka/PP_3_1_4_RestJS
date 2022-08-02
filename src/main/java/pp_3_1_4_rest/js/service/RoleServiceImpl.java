package pp_3_1_4_rest.js.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pp_3_1_4_rest.js.dao.RoleRepo;
import pp_3_1_4_rest.js.model.Role;

import java.util.Collection;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepo roleRepo;

    @Autowired
    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public Role findById(Long id) {
        return roleRepo.findById(id).get();
    }

    @Override
    public Role findByRole(String role) {
        return findByRole(role);
    }

    @Override
    public List<Role> findAllRoles() {
        return roleRepo.findAll(Sort.by(Sort.Direction.ASC,"role"));
    }

    @Override
    public void saveRole(Role role) {
        roleRepo.save(role);
    }

    @Override
    public void saveAllRoles(Collection<Role> roles) {
        for (Role role: roles) {
            roleRepo.save(role);
        }
    }
}
