package by.fedorenko.service;

import by.fedorenko.entity.Task;
import by.fedorenko.exception.ServiceException;

public interface BotService {
    void sendReports(String path) throws ServiceException;

    void sendTask(Task task) throws ServiceException;
}
