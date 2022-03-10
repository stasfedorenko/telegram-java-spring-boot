package by.fedorenko.controller;

import by.fedorenko.entity.Role;
import by.fedorenko.entity.Status;
import by.fedorenko.entity.User;
import by.fedorenko.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Controller
public class RegistrationController {


    private final UserRepository userRepository;

    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/registration")
    public String index() {
        return "/auth/registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){
        Optional<User> userFromDb = userRepository.findByEmail(user.getEmail());
        if(userFromDb.isPresent()){
            model.put("message","User exists!");
            return "/auth/registration";
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(Status.ACTIVE);
        user.setRole(Role.USER);
        userRepository.save(user);

        return "redirect:/login";
    }
}
