/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inavi.backend.user.service;

/**
 *
 * @author sebas
 */
import com.inavi.backend.user.exception.UserNotValid;
import com.inavi.backend.user.model.User;
import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImpl implements ValidationService {

    public void validateUser(User user) {
        if (user.getName() == null || user.getName().length() < 3) {
            throw new UserNotValid( "Name must be at least 4 characters long");
        }
        
        validateEmail(user.getEmail());
    }
    
    public void validateEmail(String email) {
        if (email == null || email.length() < 5) {
            throw new UserNotValid("Email must be at least 5 characters long");
        }

        if (!email.contains("@")) {
            throw new UserNotValid("Email must contain '@'");
        }

        if (!email.endsWith(".com")) {
            throw new UserNotValid("Email must end with '.com'");
        }
    }
}
