package by.fedorenko.controller;

import by.fedorenko.entity.Task;
import by.fedorenko.exception.TaskNotFoundException;
import by.fedorenko.repository.TaskJpaRepository;
import by.fedorenko.repository.UserJpaRepository;
import by.fedorenko.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
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
    public ModelAndView showTaskList() {
        ModelAndView mv = new ModelAndView("/task/tasks-page");
        mv.getModel().put("users", userJpaRepository.findAll());
        List<Task> listTasks = service.listAllTasks();
        mv.getModel().put("listTasks", listTasks);
        return mv;
    }

    @GetMapping("/new")
//    @PreAuthorize("hasAuthority('developers:write')")
    public ModelAndView showNewFormTask() {
        ModelAndView mv = new ModelAndView("/task/task_form");
        mv.getModel().put("users", userJpaRepository.findAll());
        mv.getModel().put("task", new Task());
        mv.getModel().put("pageTitle", "Add New Task");
        return mv;
    }

    @PostMapping("")
//    @PreAuthorize("hasAuthority('developers:write')")
    public ModelAndView saveTask(Task task, RedirectAttributes ra) {
        ModelAndView mv = new ModelAndView("redirect:/tasks");
        service.saveTask(task);
        ra.addFlashAttribute("message", "Yhe task has been saved successfully." );
        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView showEditTask(@PathVariable("id") Long id){
        ModelAndView mv;
        try {
            mv = new ModelAndView("/task/task_form");
            Task task = service.getTask(id);
            mv.getModel().put("users",userJpaRepository.findAll());
            mv.getModel().put("task", task);
            mv.getModel().put("pageTitle", "Update Task ID = "+id);
            return mv;
        } catch (TaskNotFoundException e) {
            mv = new ModelAndView("redirect:/tasks");
            e.printStackTrace();
        }
        return mv;
    }

    @DeleteMapping("/{id}")
    public ModelAndView deleteTask(@PathVariable("id") Long id, RedirectAttributes ra){
        ModelAndView mv = new ModelAndView("redirect:/tasks");
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "Yhe task has been deleted successfully." );
        } catch (TaskNotFoundException e) {
            e.printStackTrace();
        }
        return mv;
    }
}
