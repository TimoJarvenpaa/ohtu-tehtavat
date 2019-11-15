package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.UserDao;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        // validity check of username and password

        // username must be at least 3 characters long
        if(username.length() < 3){
            return true;
        }
        
        // password must be at least 8 characters long
        if(password.length() < 8){
            return true;
        }
        
        // a-z
        for(char c : username.toCharArray()){
            if(!Character.isLetter(c) || Character.isUpperCase(c)){
                return true;
            }
        }
        
        // password must have at least one character that is not a letter
        boolean allLetters = password.chars().allMatch(Character::isLetter);
        if(allLetters){
            return true;
        }
        
        return false;
    }
}
