/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inavi.backend.apiGateway;

import com.inavi.backend.user.service.UserServiceImpl; 
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class SomeController {

    private final UserServiceImpl userServiceImpl; 

    @Autowired
    public SomeController(UserServiceImpl userServiceImpl) { 
        this.userServiceImpl = userServiceImpl;
    }
}