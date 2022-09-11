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
@RequestMapping()
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
    @GetMapping("/adminUser")
    public String adminUserPage(@ModelAttribute("user") User user, Model model, Principal principal) {
        model.addAttribute("allRoles", roleService.getAllRoles());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("enterUser", userService.getUserByUsername(principal.getName()));
        return "AdminUserPage";
    }
    @GetMapping("/admin")
    public String adminPage(@ModelAttribute("user") User user, Model model, Principal principal) {
        model.addAttribute("allRoles", roleService.getAllRoles());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("enterUser", userService.getUserByUsername(principal.getName()));
        return "AdminPage";
    }
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user, @RequestParam(value = "role") String role, Principal principal) {
        Set<Role> set = new HashSet<>();
        set.add(roleService.getRoleByName(role));
        user.setRoles(set);
        System.out.println(user);
        userService.save(user);
        if (userService.getUserByUsername(principal.getName()).getRoles().contains(roleService.getRoleByName("ROLE_USER"))) {
            System.out.println(userService.getUserByUsername(principal.getName()));
            return "redirect:/adminUser";
        } else return "redirect:/admin";
    }
    @GetMapping ("/delete")
    public String deleteUserbyId(@ModelAttribute("user") User user, Principal principal) {
        userService.deleteById(user.getId());
        if (userService.getUserByUsername(principal.getName()).getRoles().contains(roleService.getRoleByName("ROLE_USER"))) {
            return "redirect:/adminUser";
        } else return "redirect:/admin";
    }
}
