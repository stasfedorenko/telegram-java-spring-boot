package by.fedorenko.service;


import by.fedorenko.entity.Task;
import by.fedorenko.exception.TaskNotFoundException;

import java.util.List;

public interface TaskService {

    List<Task> listAllTasks();

    void saveTask(Task user);

    Task getTask(Long id) throws TaskNotFoundException;

    void delete(Long id) throws TaskNotFoundException;

}