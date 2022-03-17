package by.fedorenko.controller;

import by.fedorenko.entity.User;
import by.fedorenko.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@Controller
public class LoginRedController {

    private final UserService userService;

    public LoginRedController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String index(@ModelAttribute("user") User user) {
        return "/auth/registration";
    }

    @GetMapping("/login")
    public String login() {
        return "/auth/login";
    }

    @PostMapping("/login")
    public String loginSave() {
        return "redirect:/";
    }

    @PostMapping("/registration")
    public String addUser(@Valid User user, BindingResult bindingResult, Map<String, Object> model) {
        if(bindingResult.hasErrors()){
            return "/auth/registration";
        }else{
            Optional<User> userFromDb = userService.findByEmail(user.getEmail());
            if (userFromDb.isPresent()) {
                model.put("message", "User exists!");
                return "/auth/registration";
            }
            userService.saveUser(user, true);
            return "redirect:/";
        }

    }
}
