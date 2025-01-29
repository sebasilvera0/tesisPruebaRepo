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

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ValidationService validationService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ValidationService validationService) {
        this.userRepository = userRepository;
        this.validationService = validationService;
    }
    
   

    @Override
    public User registerUser(User user) {
        validationService.validateUser(user);
        return userRepository.save(user);
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
