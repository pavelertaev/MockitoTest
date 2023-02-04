package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


public class UserRepositoryTest {


    UserRepository userRepository;


    @Test
    public void getAllUserByNull(){
        UserRepository userRepository = new UserRepository();
        List<User> expected = userRepository.getAllUser().stream().toList();
        assertEquals(expected,new ArrayList<User>());

    }

    @Test
    public void getAllUser(){
        UserRepository userRepository = new UserRepository();
        User user = new User("pavel","123");
        User user1 = new User("ant","1234");
        userRepository.addUser(user);
        userRepository.addUser(user1);
        List<User> expected = new ArrayList<User>();
        expected.add(user);
        expected.add(user1);
        List<User> actual = (List<User>) userRepository.getAllUser();

        assertEquals(expected,actual);
    }

    @Test
    public void searchUserByLogin(){
        UserRepository userRepository = new UserRepository();
        User user = new User("pavel","123");
        User user1 = new User("ant","1234");
        userRepository.addUser(user);
        userRepository.addUser(user1);
        Optional<User> expected = userRepository.getUserByLogin(user.getLogin());
        Optional<User> actual = userRepository.getUserByLogin(user.getLogin());
        assertEquals(expected,actual);

    }
    @Test
    public void searchUserByLoginIfUserNotFound(){
        UserRepository userRepository = new UserRepository();
        User user = new User("pavel","123");
        User user1 = new User("ant","1234");
        userRepository.addUser(user);
        userRepository.addUser(user1);
        Optional<User> expected = userRepository.getUserByLogin(user.getLogin());

        assertEquals(expected,new User("123","123"));
    }

    @Test
    public void searchUserByLoginAndPassword(){
        UserRepository userRepository = new UserRepository();
        User user = new User("pavel","123");
        User user1 = new User("ant","1234");
        userRepository.addUser(user);
        userRepository.addUser(user1);
        Optional<User> expected = userRepository.getUserByLoginAndPassword(user.getLogin(), user.getPassword());
        Optional<User> actual = userRepository.getUserByLoginAndPassword(user.getLogin(), user.getPassword());
        assertEquals(expected,actual);
    }

    @Test
    public void searchUserByPasswordNotEqualsLogin(){
        UserRepository userRepository = new UserRepository();
        User user = new User("pavel","123");
        User user1 = new User("ant","1234");
        userRepository.addUser(user);
        userRepository.addUser(user1);
        Optional<User> expected = userRepository.getUserByLoginAndPassword("123", user.getPassword());
        Optional<User> actual = userRepository.getUserByLoginAndPassword(user.getLogin(), user.getPassword());
        assertEquals(expected,actual);
    }

    @Test
    public void searchUserByLoginNotEqualsPassword(){
        UserRepository userRepository = new UserRepository();
        User user = new User("pavel","123");
        User user1 = new User("ant","1234");
        userRepository.addUser(user);
        userRepository.addUser(user1);
        Optional<User> expected = userRepository.getUserByLoginAndPassword(user.getLogin(),"234");
        Optional<User> actual = userRepository.getUserByLoginAndPassword(user.getLogin(), user.getPassword());
        assertEquals(expected,actual);
    }


}
