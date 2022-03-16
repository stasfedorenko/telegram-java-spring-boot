package by.fedorenko.service.impl;

import by.fedorenko.entity.Task;
import by.fedorenko.exception.TaskNotFoundException;
import by.fedorenko.repository.TaskRepository;
import by.fedorenko.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> listAllTasks() {
        return (List<Task>) taskRepository.findAll();
    }

    @Override
    public void saveTask(Task user) {
        taskRepository.save(user);
    }

    @Override
    public Task getTask(Long id) throws TaskNotFoundException {
        Optional<Task> result = taskRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        else{
            throw new TaskNotFoundException("Couldn't find any users with ID = "+id);
        }
    }

    @Override
    public void delete(Long id) throws TaskNotFoundException {
        Optional<Task> result = taskRepository.findById(id);
        if(result.isPresent()){
            taskRepository.delete(result.get());
        }
        else{
            throw new TaskNotFoundException("Couldn't find any users with ID = "+id);
        }
    }
}
