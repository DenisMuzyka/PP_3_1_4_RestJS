package ru.kata.spring.boot_security.bootstrap.service;

import ru.kata.spring.boot_security.bootstrap.model.Role;

import java.util.List;

public interface RoleService {
    Role findById(Long id);
    Role findByRole(String role);
    List<Role> findAllRoles();
}
