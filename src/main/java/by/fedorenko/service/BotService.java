package by.fedorenko.service;

import by.fedorenko.exception.ServiceException;

public interface BotService {
    void runBot(String path) throws ServiceException;
}
