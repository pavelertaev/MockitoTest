package org.example;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    User user = new User("pavel","123");
    User user1 = new User("anton","1234");
    User user2 = new User("sergei","1235");

    public List<String> getAllLoginByUser(String login) {
        return userRepository.getAllUser().stream().map(User::getLogin).collect(Collectors.toList());
    }

    public void createUser(String login, String password) {
        User user = new User(login, password);
        if (!userRepository.getUserByLoginAndPassword(user.getLogin(), user.getPassword()).isEmpty()) {
            throw new UserNonUniqueException();
        }
        userRepository.addUser(user);
    }
    public boolean isContainsUser(String login , String password){
        User user = new User(login,password);
        if(userRepository.getUserByLoginAndPassword(user.getLogin(), user.getPassword()).equals(user)) {
            return true;
        }else {
            return false;
        }
    }
    public boolean isCorrectedLoginOrPassword(String login , String password){
        if (!login.isBlank() && !password.isBlank()) {
            throw new IllegalArgumentException();
        }
        return true;
    }

}
