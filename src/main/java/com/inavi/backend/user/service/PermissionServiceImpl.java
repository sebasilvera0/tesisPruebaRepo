/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inavi.backend.user.service;

import com.inavi.backend.user.exception.NotFound;
import com.inavi.backend.user.model.Permission;
import com.inavi.backend.user.model.Role;
import com.inavi.backend.user.repository.PermissionRepository;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;
    
    @Autowired
    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }
    
    @Override
    public Permission getPermissionById(Integer permissionId) {
        return permissionRepository.findById(permissionId)
                .orElseThrow(() -> new NotFound("Permission", permissionId));
    }
    
    @Override
    public Permission createPermission(Permission permission) {
        return permissionRepository.save(permission);
    }
    
    public Set<Permission> getPermisionByIds(Set<Integer> roleIds) {
        return new HashSet<>(permissionRepository.findAllById(roleIds));
    }

}
