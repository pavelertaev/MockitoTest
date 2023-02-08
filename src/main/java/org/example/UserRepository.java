package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class UserRepository {
    private List<User> users = new ArrayList<>();


    public void addUser(User user){
        this.users.add(user);
    }

    public Collection<User> getAllUser(){
        return users;
    }

    public Optional<User> getUserByLogin(String login){
        return  users.stream().filter(user -> user.getLogin().equals(login)).findFirst();
    }

    public Optional<User> getUserByLoginAndPassword(String login , String password){
            return  users.stream().filter(user -> user.getLogin().equals(login)&&user.getPassword().equals(password)).findFirst();
        }
    }

