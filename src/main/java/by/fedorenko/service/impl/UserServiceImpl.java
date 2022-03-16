package by.fedorenko.service.impl;

import by.fedorenko.entity.User;
import by.fedorenko.exception.UserNotFoundException;
import by.fedorenko.repository.UserJpaRepository;
import by.fedorenko.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserJpaRepository userJpaRepository;

    public UserServiceImpl(UserJpaRepository userJpaRepository) {

        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public List<User> listAll() {
        return userJpaRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        userJpaRepository.save(user);
    }

    @Override
    public User getUser(Long id) throws UserNotFoundException {
        Optional<User> result = userJpaRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new UserNotFoundException("Couldn't find any users with ID = " + id);
        }
    }

    @Override
    public void delete(Long id) throws UserNotFoundException {
        Optional<User> result = userJpaRepository.findById(id);
        if (result.isPresent()) {
            userJpaRepository.delete(result.get());
        } else {
            throw new UserNotFoundException("Couldn't find any users with ID = " + id);
        }
    }
}
