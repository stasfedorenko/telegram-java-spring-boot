package by.fedorenko.service;

import by.fedorenko.entity.User;
import by.fedorenko.exception.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> listAll();

    void saveUser(User user);

    void saveUser(User user,Boolean reg);

    User getUser(Long id) throws UserNotFoundException;

    void delete(Long id) throws UserNotFoundException;

    Optional<User> findByEmail(String email);

}
