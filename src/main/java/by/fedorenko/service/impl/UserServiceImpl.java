package by.fedorenko.service.impl;

import by.fedorenko.entity.User;
import by.fedorenko.exception.UserNotFoundException;
import by.fedorenko.repository.UserRepository;
import by.fedorenko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> listAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUser(Long id) throws UserNotFoundException {
        Optional<User> result = userRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        else{
            throw new UserNotFoundException("Couldn't find any users with ID = "+id);
        }
    }

    @Override
    public void delete(Long id) throws UserNotFoundException {
        Optional<User> result = userRepository.findById(id);
        if(result.isPresent()){
            userRepository.delete(result.get());
        }
        else{
            throw new UserNotFoundException("Couldn't find any users with ID = "+id);
        }
    }
}
