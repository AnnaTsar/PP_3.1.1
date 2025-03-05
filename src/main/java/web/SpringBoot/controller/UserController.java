package web.SpringBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.SpringBoot.model.User;
import web.SpringBoot.servise.UserService;


@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }


    @GetMapping("add")
    public String newUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "addUser";
    }

    @PostMapping("add")
    public String createNewUser(@ModelAttribute("user") User user) {

        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("edit/{id}")
    public String editUser(Model model, @PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping("edit")
    public String updateUser(@ModelAttribute("user") User user) {

        userService.editUser(user);
        return "redirect:/users";
    }

    @PostMapping("delete")
    public String deleteUserById(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("{id}")
    public String showUserPage(@PathVariable("id") Long id, Model modelMap) {
        modelMap.addAttribute("user", userService.getUserById(id));
        return "show";
    }

}
