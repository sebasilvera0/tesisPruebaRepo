package com.inavi.backend.user.service;

import com.inavi.backend.user.model.Role;

import java.util.List;

public interface RoleService {

    Role createRole(Role role);

    Role getRoleById(Integer roleId);

    List<Role> getAllRoles();

    Role updateRole(Role role);

    void deleteRole(Integer roleId);
    
    Role addPermissionToRole(Integer roleId, Integer permissionId);
}
