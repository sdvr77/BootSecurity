package ru.kata.spring.boot_security.demo;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.Set;

public class InitApp {
    public InitApp(UserService userService, RoleService roleService) {

        Role adminRole = new Role("ROLE_ADMIN");
        roleService.save(adminRole);
        Role userRole = new Role("ROLE_USER");
        roleService.save(userRole);

        Set<Role> rolesUser1 = new HashSet<>();
        rolesUser1.add(userRole);
        User user1 = new User("User1", "User1SurName", 22, "user1", "user1", rolesUser1);
        userService.save(user1);

        Set<Role> rolesUser2 = new HashSet<>();
        rolesUser2.add(userRole);
        User user2 = new User("User2", "User2SurName", 33, "user2", "user2", rolesUser2);
        userService.save(user2);

        Set<Role> rolesUser3 = new HashSet<>();
        rolesUser3.add(userRole);
        User user3 = new User("User3", "User3SurName", 34, "user3", "user3", rolesUser3);
        userService.save(user3);

        Set<Role> rolesUser4 = new HashSet<>();
        rolesUser4.add(userRole);
        User user4 = new User("User4", "User4SurName", 35, "user4", "user4", rolesUser4);
        userService.save(user4);

        Set<Role> rolesAdmin = new HashSet<>();
        rolesAdmin.add(adminRole);
        User admin = new User("Admin", "AdminSurName", 44, "admin", "admin", rolesAdmin);
        userService.save(admin);

        Set<Role> rolesUserAdmin = new HashSet<>();
        rolesUserAdmin.add(adminRole);
        rolesUserAdmin.add(userRole);
        User userAdmin = new User("userAdmin", "userAdminSurName", 55, "userAdmin", "userAdmin", rolesUserAdmin);
        userService.save(userAdmin);
    }
}
