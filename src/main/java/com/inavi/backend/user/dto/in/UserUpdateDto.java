/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inavi.backend.user.dto.in;

import javax.validation.constraints.Email;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {
    
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private Integer id;
    
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

    private String password;

    private Set<Integer> vineyardAccess;
    
    private Integer productiveUnit;
    
    @NotNull(message = "Roles cannot be null")
    @NotEmpty(message = "At least one role must be provided")
    private Set<Integer> roles;

   
}
