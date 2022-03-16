package by.fedorenko.service;

import by.fedorenko.entity.User;
import by.fedorenko.exception.UserNotFoundException;

import java.util.List;

public interface UserService {

    List<User> listAll();

    void saveUser(User user);

    User getUser(Long id) throws UserNotFoundException;

    void delete(Long id) throws UserNotFoundException;

}
