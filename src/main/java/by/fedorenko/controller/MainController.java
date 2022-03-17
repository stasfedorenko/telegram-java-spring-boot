package by.fedorenko.controller;

import by.fedorenko.bots.MyBot;
import by.fedorenko.entity.User;
import by.fedorenko.repository.UserJpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    private final UserJpaRepository userJpaRepository;

    public MainController(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
    MyBot bot = MyBot.getInstance();
        model.addAttribute("bot", bot);
        return "index";
    }

    @GetMapping("/user")
    public String showUserPage(Model model) {
        List<User> users = userJpaRepository.findAll();
        model.addAttribute("users", users);
        return "/user/user-page";
    }
}
