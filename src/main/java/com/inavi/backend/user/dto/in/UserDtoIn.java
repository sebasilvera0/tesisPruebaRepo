/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inavi.backend.user.dto.in;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

public class UserDtoIn {
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @NotBlank(message = "Surname cannot be blank")
    @Size(min = 3, message = "Surname must be at least 3 characters long")
    private String surname;

    @NotBlank(message = "National Identification cannot be blank")
    private String nationalIdentification;

    @Email(message = "Invalid email address")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    private Set<Integer> vineyardAccess;
    
    @NotNull(message = "Roles cannot be null")
    @NotEmpty(message = "At least one role must be provided")
    private Set<Integer> roles;

    // Constructor, Getters and Setters
    public UserDtoIn(String name, String surname, String nationalIdentification, String email, String password, Set<Integer> vineyardAccess, Set<Integer> roles) {
        this.name = name;
        this.surname = surname;
        this.nationalIdentification = nationalIdentification;
        this.email = email;
        this.password = password;
        this.vineyardAccess = vineyardAccess;
        this.roles = roles;
    }
}
