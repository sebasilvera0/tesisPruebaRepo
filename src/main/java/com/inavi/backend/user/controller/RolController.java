/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inavi.backend.user.controller;

import com.inavi.backend.user.model.User;
import com.inavi.backend.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.inavi.backend.user.dto.in.UserDtoIn;
import com.inavi.backend.user.dto.in.UserUpdateDto;
import com.inavi.backend.user.service.RoleService;
@RestController  
@RequestMapping("/api/rol") 
public class RolController {
    
    @Autowired
    private RoleService roleService;

    // Registrar un nuevo usuario
    @PostMapping()
    public ResponseEntity<User> registerUser(@RequestBody UserDtoIn userDtoIn) {
        User createdUser = userService.registerUser(userDtoIn);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // Obtener un usuario por ID
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Integer userId) {
        User user = userService.getUserById(userId);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Iniciar sesión con correo y contraseña
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestParam String email, @RequestParam String rawPassword) {
        User user = userService.login(email, rawPassword);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    // Actualizar un usuario
    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody UserUpdateDto userUpdateDto) {
        User updatedUser = userService.updateUser(userUpdateDto);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

