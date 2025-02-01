/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inavi.backend.apiGateway;


import com.inavi.backend.user.service.UserService;
import com.inavi.backend.user.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class SomeController {

    private final UserService userService; // Inyectamos la interfaz (esto debe funcionar)

    @Autowired
    public SomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUser")
    public User getUser(@RequestParam Integer id) {
        return userService.getUserById(id);
    }
}
