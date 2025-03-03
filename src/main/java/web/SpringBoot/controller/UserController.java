package web.SpringBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.SpringBoot.model.User;
import web.SpringBoot.servise.UserService;


@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String redirectUsersPage() {
        return "redirect:/users";
    }

    @GetMapping(value = "users")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping(value = "users/add")
    public String newUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "addUser";
    }

    @PostMapping(value = "users/add")
    public String createNewUser(@ModelAttribute("user") User user) {

        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping(value = "users/edit/{id}")
    public String editUser(Model model, @PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping(value = "users/edit")
    public String updateUser(@ModelAttribute("user") User user) {

        userService.editUser(user);
        return "redirect:/";
    }

    @PostMapping("users/delete")
    public String deleteUserById(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

    @GetMapping("users/{id}")
    public String showUserPage(@PathVariable("id") Long id, Model modelMap) {
        modelMap.addAttribute("user", userService.getUserById(id));
        return "show";
    }

}
