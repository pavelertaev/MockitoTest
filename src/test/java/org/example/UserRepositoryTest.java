package org.example;

import org.junit.jupiter.api.Assertions;
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
        User userTest = new User("test", "test");
        userRepository.addUser(userTest);
        User user = userRepository.getUserByLogin("test").get();
        Assertions.assertEquals(userTest, user);

    }
    @Test
    void getNullUserByLogin() {
        User userTest = new User("test", "test");
        userRepository.addUser(userTest);
        User user = userRepository.getUserByLogin("test2").orElse(null);
        Assertions.assertNull(user);
    }

    @Test
    public void searchUserByPasswordNotEqualsLogin(){
        User userTest = new User("test", "test");
        userRepository.addUser(userTest);
        User user = userRepository.getUserByLoginAndPassword("NotTest", "test").orElse(null);
        Assertions.assertNull(user);
    }

    @Test
    public void searchUserByLoginNotEqualsPassword(){
        User userTest = new User("test", "test");
        userRepository.addUser(userTest);
        User user = userRepository.getUserByLoginAndPassword("test", "NoTest").orElse(null);
        Assertions.assertNull(user);
    }


}
