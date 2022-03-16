package by.fedorenko.controller;

import by.fedorenko.entity.User;
import by.fedorenko.exception.UserNotFoundException;
import by.fedorenko.repository.TaskJpaRepository;
import by.fedorenko.repository.UserJpaRepository;
import by.fedorenko.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService service;
    private final TaskJpaRepository taskJpaRepository;

    public UserController(UserService service, TaskJpaRepository taskJpaRepository, UserJpaRepository userJpaRepository) {
        this.service = service;
        this.taskJpaRepository = taskJpaRepository;
    }

    @GetMapping("")
//    @PreAuthorize("hasAuthority('developers:read')")
    public String showUserList(Model model) {
        model.addAttribute("tasks", taskJpaRepository.findAll());
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);
        return "/user/users-page";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("tasks", taskJpaRepository.findAll());
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add New User");
        return "/user/user_form";
    }

    @PostMapping("")
    public String saveUser(User user, RedirectAttributes ra) {
        //todo
        if(!user.getPassword().isEmpty()){
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            service.saveUser(user);
            ra.addFlashAttribute("message", "Yhe user has been saved successfully.");
        }
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String showEditUser(@PathVariable("id") Long id, Model model) {
        try {
            User user = service.getUser(id);
            model.addAttribute("tasks", taskJpaRepository.findAll());
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Update User ID = " + id);
            return "/user/user_form";
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "Yhe user has been deleted successfully.");
            return "redirect:/users";
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        return "redirect:/users";
    }
}
