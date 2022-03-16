package by.fedorenko.service.impl;

import by.fedorenko.entity.Task;
import by.fedorenko.exception.TaskNotFoundException;
import by.fedorenko.repository.TaskJpaRepository;
import by.fedorenko.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskJpaRepository taskJpaRepository;

    public TaskServiceImpl(TaskJpaRepository taskJpaRepository) {
        this.taskJpaRepository = taskJpaRepository;
    }

    @Override
    public List<Task> listAllTasks() {
        return (List<Task>) taskJpaRepository.findAll();
    }

    @Override
    public void saveTask(Task user) {
        taskJpaRepository.save(user);
    }

    @Override
    public Task getTask(Long id) throws TaskNotFoundException {
        Optional<Task> result = taskJpaRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        else{
            throw new TaskNotFoundException("Couldn't find any users with ID = "+id);
        }
    }

    @Override
    public void delete(Long id) throws TaskNotFoundException {
        Optional<Task> result = taskJpaRepository.findById(id);
        if(result.isPresent()){
            taskJpaRepository.delete(result.get());
        }
        else{
            throw new TaskNotFoundException("Couldn't find any users with ID = "+id);
        }
    }
}
