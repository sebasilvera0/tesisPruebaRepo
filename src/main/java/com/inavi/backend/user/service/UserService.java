/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.inavi.backend.user.service;

/**
 *
 * @author sebas
 */

import com.inavi.backend.user.dto.in.UserDtoIn;
import com.inavi.backend.user.dto.in.UserUpdateDto;
import com.inavi.backend.user.model.User;
import java.util.List;

public interface UserService {
    
    User registerUser(UserDtoIn user);
    
    User getUserById(Integer userId);
    
    User login(String email, String rawPassword);

    User updateUser(UserUpdateDto user);
}
