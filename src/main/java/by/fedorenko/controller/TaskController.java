package by.fedorenko.controller;

import by.fedorenko.entity.Task;
import by.fedorenko.exception.TaskNotFoundException;
import by.fedorenko.repository.TaskJpaRepository;
import by.fedorenko.repository.UserJpaRepository;
import by.fedorenko.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;
    private final UserJpaRepository userJpaRepository;

    public TaskController(TaskService service, TaskJpaRepository taskJpaRepository, UserJpaRepository userJpaRepository) {
        this.service = service;
        this.userJpaRepository = userJpaRepository;
    }

    @GetMapping("")
//    @PreAuthorize("hasAuthority('developers:read')")
    public String showTaskList(Model model) {
        model.addAttribute("users", userJpaRepository.findAll());
        List<Task> listTasks = service.listAllTasks();
        model.addAttribute("listTasks", listTasks);
        return "/task/tasks-page";
    }

    @GetMapping("/new")
//    @PreAuthorize("hasAuthority('developers:write')")
    public String showNewFormTask(Model model) {
        model.addAttribute("users", userJpaRepository.findAll());
        model.addAttribute("task", new Task());
        model.addAttribute("pageTitle", "Add New Task");
        return "/task/task_form";
    }

    @PostMapping("")
//    @PreAuthorize("hasAuthority('developers:write')")
    public String saveTask(Task task, RedirectAttributes ra) {
        service.saveTask(task);
        ra.addFlashAttribute("message", "Yhe task has been saved successfully." );
        return "redirect:/tasks";
    }

    @GetMapping("/{id}")
    public String showEditTask(@PathVariable("id") Long id, Model model){

        try {
            Task task = service.getTask(id);
            model.addAttribute("users",userJpaRepository.findAll());
            model.addAttribute("task", task);
            model.addAttribute("pageTitle", "Update Task ID = "+id);
            return "/task/task_form";
        } catch (TaskNotFoundException e) {
            e.printStackTrace();
        }
        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id") Long id, RedirectAttributes ra){
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "Yhe task has been deleted successfully." );
            return "redirect:/tasks";
        } catch (TaskNotFoundException e) {
            e.printStackTrace();
        }
        return "redirect:/tasks";
    }
}
