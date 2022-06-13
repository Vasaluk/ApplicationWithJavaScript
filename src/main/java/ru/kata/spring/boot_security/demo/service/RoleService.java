package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleService {
    void saveRole(Role user);

    Role getRole(Long id);

    List<Role> listAll();
}
