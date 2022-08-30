package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping("/users")
    public String printAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/user")
    public String printUser(Principal principal, Model model) {
        model.addAttribute("users", userService.getUserByUsername(principal.getName()));
        return "user";
    }
    @GetMapping("/addUser")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        return "newUserInfo";
    }
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        if (user.getRoles() == null) {
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.getRoleByName("ROLE_USER"));
            user.setRoles(roles);
        }
        userService.save(user);
        return "redirect:/admin/users";
    }
    @GetMapping("/updateUser")
    public String updateUser(@ModelAttribute("user") User user) {
        return "updateUserInfo";
    }

    @GetMapping("/delete")
    public String deleteUserbyId(@ModelAttribute("user") User user) {
        userService.deleteById(user.getId());
        return "redirect:/admin/users";
    }
    @GetMapping("/deleteUser")
    public String deleteUser(@ModelAttribute("user") User user) {
        return "deleteUser";
    }
}
