/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inavi.backend.user.dto.out;

import com.inavi.backend.user.dto.in.*;
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

public class UserDtoOut {

    private Integer id;
    private String name;
    private String surname;
    private String nationalIdentification;
    private String email;
    private Set<Integer> vineyardAccess;
    private Set<String> roles;
    

     public UserDtoOut(Integer id, String name, String surname, String nationalIdentification, String email, Set<Integer> vineyardAccess, Set<String> roles) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.nationalIdentification = nationalIdentification;
        this.email = email;
        this.vineyardAccess = vineyardAccess;
        this.roles = roles;
    }
}
