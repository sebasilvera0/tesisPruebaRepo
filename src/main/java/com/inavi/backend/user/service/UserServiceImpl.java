/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inavi.backend.user.service;

/**
 *
 * @author sebas
 */
import com.inavi.backend.user.exception.NotFound;
import com.inavi.backend.user.model.User;
import com.inavi.backend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ValidationService validationService;
    private final PasswordEncoder passwordEncoder;

     @Autowired
    public UserServiceImpl(UserRepository userRepository, ValidationService validationService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.validationService = validationService;
        this.passwordEncoder = passwordEncoder; // Spring inyecta la instancia creada en SecurityConfig
    }
    
   

    @Override
    public User registerUser(User user) {
        validationService.validateUser(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    
    public User login(String email, String rawPassword) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new NotFound("User", email);
        }
        
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new NotFound("User", email); 
        }

        return user;
    }

    
    @Override
    public User getUserById(Integer userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new NotFound("User",  userId  ));
    }

   

    @Override
    public User updateUser(User user) {
        
        this.getUserById(user.getId());
        
        validationService.validateUser(user);
        
        return userRepository.save(user);
    }

    

    


}
