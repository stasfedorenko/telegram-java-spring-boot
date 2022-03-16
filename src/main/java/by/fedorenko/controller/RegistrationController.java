package by.fedorenko.controller;

import by.fedorenko.entity.Role;
import by.fedorenko.entity.Status;
import by.fedorenko.entity.User;
import by.fedorenko.repository.UserJpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class RegistrationController {

    private final UserJpaRepository userJpaRepository;

    public RegistrationController(UserJpaRepository userRepository) {
        this.userJpaRepository = userRepository;
    }

    @GetMapping("/registration")
    public String index() {
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

    @GetMapping("/successfulLogin")
    public String success(Model model) {
        model.addAttribute("loginSuccess",true);
        return "/index";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){
        Optional<User> userFromDb = userJpaRepository.findByEmail(user.getEmail());
        if(userFromDb.isPresent()){
            model.put("message","User exists!");
            return "/auth/registration";
        }
        if(
                user.getEmail().isEmpty()||
                user.getPassword().isEmpty()||
                user.getFirstName().isEmpty()||
                user.getLastName().isEmpty())
        {
            model.put("message","Field is empty!");
            return "/auth/registration";
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(Status.ACTIVE);
        user.setRole(Role.USER);
        userJpaRepository.save(user);

        return "redirect:/";
    }
}
