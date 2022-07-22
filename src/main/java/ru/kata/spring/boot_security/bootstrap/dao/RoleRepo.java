package ru.kata.spring.boot_security.bootstrap.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.bootstrap.model.Role;

import java.util.Collection;


public interface RoleRepo extends JpaRepository<Role, Long> {

    Role findByRole(String role);
    Collection<Role> findAllByUsersId(long id);
}
