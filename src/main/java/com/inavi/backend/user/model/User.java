/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inavi.backend.user.model;

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

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, message = "Name must be at least 4 characters long")
    private String name; 
    
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, message = "Name must be at least 4 characters long")
    private String surname;
    
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 3, message = "Name must be at least 4 characters long")
    private String nationalIdentification;
    
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;
    
    @ManyToOne(fetch = FetchType.EAGER)  // Relación con ProductiveUnit
    @JoinColumn(name = "productive_unit_id", nullable = true)  // La unidad productiva puede ser null
    private ProductiveUnit productiveUnit; 

    @ElementCollection
    @CollectionTable(name = "user_vineyard_access", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "vineyard_id")
    private Set<Integer> vineyardAccess; // Usamos un Set para evitar duplicados

    @ManyToMany
    @JoinTable(
        name = "user_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
    
    
    
    
    

    
}
