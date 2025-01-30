/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inavi.backend.user.service;

import com.inavi.backend.user.exception.NotFound;
import com.inavi.backend.user.model.Permission;
import com.inavi.backend.user.model.Role;
import com.inavi.backend.user.repository.RoleRepository;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final PermissionService permissionService; 

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, PermissionService permissionService) {
        this.roleRepository = roleRepository;
        this.permissionService = permissionService;  // Asignar el servicio de permisos
    }

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getRoleById(Integer roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new NotFound("Role", roleId));
    }
    
    public Set<Role> getRolesByIds(Set<Integer> roleIds) {
        return new HashSet<>(roleRepository.findAllById(roleIds));
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role updateRole(Role role) {
        getRoleById(role.getId()); // Verificar si el rol existe
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Integer roleId) {
        Role role = getRoleById(roleId); // Verificar si el rol existe
        roleRepository.delete(role);
    }
    
    @Override
    public Role addPermissionToRole(Integer roleId, Integer permissionId) {
        // Buscar el rol
        Role role = getRoleById(roleId);
        
        Permission permission = permissionService.getPermissionById(permissionId);

        role.getPermissions().add(permission);

        return roleRepository.save(role);
    }
}
