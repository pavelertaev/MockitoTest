package org.example;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    public UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<String> getAllLoginByUser(String login) {
        return userRepository.getAllUser().stream().map(User::getLogin).collect(Collectors.toList());
    }

    public void createUser(String login, String password) {
        User user = new User(login, password);
        if (isCorrectedLoginOrPassword(login, password) && isUnique(user)) {
            userRepository.addUser(user);
        }
    }
    public boolean isContainsUser(User user){
        return userRepository.getUserByLoginAndPassword(user.getLogin(), user.getPassword()).equals(user);

    }
    public boolean isCorrectedLoginOrPassword(String login , String password){
        if (login.isBlank() && password.isBlank()) {
            throw new IllegalArgumentException();
        }
        return true;
    }

    private boolean isUnique(User user){
        if (userRepository.getAllUser().contains(user)) {
            throw new UserNonUniqueException();
        }
        return true;
    }


}
