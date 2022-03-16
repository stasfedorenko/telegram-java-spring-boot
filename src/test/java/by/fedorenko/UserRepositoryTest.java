package by.fedorenko;


import by.fedorenko.entity.Role;
import by.fedorenko.entity.Status;
import by.fedorenko.entity.User;
import by.fedorenko.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.Assert;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {
    @Autowired private UserRepository repo;



    @Test
    public void testAddNew(){
        User user = new User();
        user.setEmail("test@mail.com");
        user.setPassword("test");
        user.setFirstName("test");
        user.setLastName("test");
        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);
        long countBefore = repo.count();
        User savedUser = repo.save(user);
        long countAfter = repo.count();

        Assertions.assertEquals(1, countAfter - countBefore);

        Assertions.assertFalse(savedUser.getEmail().isEmpty());
        Assertions.assertTrue(savedUser.getId()>0);
    }

    @Test
    public void testListAll(){
        Iterable<User> users = repo.findAll();
        Assertions.assertTrue(repo.count()>0);
        for(User user: users){
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate(){
        long userId = 2L;
        User user = repo.findById(userId).get();
        user.setStatus(Status.BANNED);
        repo.save(user);

        User updateUser = repo.findById(userId).get();
        Assertions.assertTrue(updateUser.getStatus().equals(Status.BANNED));

        updateUser.setStatus(Status.ACTIVE);
        repo.save(updateUser);

        updateUser = repo.findById(userId).get();
        Assertions.assertTrue(updateUser.getStatus().equals(Status.ACTIVE));
    }

    @Test
    public void testGet(){
        Long userId = 2L;
        Optional<User> optionalUser = repo.findById(userId);
        Assertions.assertTrue(optionalUser.isPresent());
    }

    @Test
    public void testDelete(){
        Long userId = 20L;
        Optional<User> optionalUser = repo.findById(userId);
        long countBefore = repo.count();
        repo.delete(optionalUser.get());
        long countAfter = repo.count();

        Assertions.assertEquals(1,countBefore-countAfter);

    }

}
