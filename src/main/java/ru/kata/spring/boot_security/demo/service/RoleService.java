package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;


import java.util.List;

public interface RoleService {
    List<Role> getAllUsers();
    void save(Role role);
    void deleteById(int id);
    Role getRoleById(int id);
    Role getRoleByName(String name);
}
