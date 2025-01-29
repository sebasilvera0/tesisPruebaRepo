/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inavi.backend.user.repository;


import com.inavi.backend.user.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    
}
