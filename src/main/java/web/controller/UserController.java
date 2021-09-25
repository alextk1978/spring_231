package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
public class UserController {

    private UserService userServiceImpl;

    public UserController() {
    }

    @Autowired
    public UserController(UserService userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userServiceImpl.getAllUsers());
        return "index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userServiceImpl.getUserById(id));
        return "show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userServiceImpl.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userServiceImpl.getUserById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userServiceImpl.updateUser(user, id);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userServiceImpl.deleteUserById(id);
        return "redirect:/";
    }
}
