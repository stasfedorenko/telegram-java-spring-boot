package by.fedorenko.controller;

import by.fedorenko.entity.User;
import by.fedorenko.exception.UserNotFoundException;
import by.fedorenko.repository.TaskJpaRepository;
import by.fedorenko.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@PreAuthorize("hasAuthority('read')")
public class UserController {

    private final UserService service;
    private final TaskJpaRepository taskJpaRepository;

    public UserController(UserService service, TaskJpaRepository taskJpaRepository) {
        this.service = service;
        this.taskJpaRepository = taskJpaRepository;
    }

    @GetMapping("")
    public ModelAndView showUsersList() {
        ModelAndView mv = new ModelAndView("/user/users-page");
        mv.getModel().put("tasks", taskJpaRepository.findAll());
        List<User> listUsers = service.listAll();
        mv.getModel().put("listUsers", listUsers);
        return mv;
    }

    @GetMapping("/new")
    @PreAuthorize("hasAuthority('write')")
    public ModelAndView showNewForm() {
        ModelAndView mv = new ModelAndView("/user/user_form");
        mv.getModel().put("tasks", taskJpaRepository.findAll());
        mv.getModel().put("user", new User());
        mv.getModel().put("pageTitle", "Add New User");
        return mv;
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('write')")
    public ModelAndView saveOrUpdateUser(@Valid User user,
                                         BindingResult bindingResult, RedirectAttributes ra) {
        ModelAndView mv;
        if(bindingResult.hasErrors()){
            mv = new ModelAndView("/user/user_form");
            mv.getModel().put("tasks", taskJpaRepository.findAll());
            return mv;
        }
        else{
            mv = new ModelAndView("redirect:/users");
            service.saveUser(user);
            ra.addFlashAttribute("message", "Yhe user has been saved successfully.");
        }
        return mv;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('write')")
    public ModelAndView showEditUser(@PathVariable("id") Long id) {
        ModelAndView mv;
        try {
            mv = new ModelAndView("/user/user_form");
            User user = service.getUser(id);
            mv.getModel().put("tasks", taskJpaRepository.findAll());
            mv.getModel().put("user", user);
            mv.getModel().put("pageTitle", "Update User ID = " + id);
            return mv;
        } catch (UserNotFoundException e) {
            mv = new ModelAndView("/user/users-page");
            e.printStackTrace();
        }
        return mv;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('write')")
    public ModelAndView deleteUser(@PathVariable("id") Long id, RedirectAttributes ra) {
        ModelAndView mv = new ModelAndView("redirect:/users");
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "Yhe user has been deleted successfully.");
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        return mv;
    }
}
