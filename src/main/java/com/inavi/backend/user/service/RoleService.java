package com.inavi.backend.user.service;

import com.inavi.backend.user.dto.in.RolDtoIn;
import com.inavi.backend.user.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {

    Role createRole(RolDtoIn rolDtoIn);

    Role getRoleById(Integer roleId);
    
    Set<Role> getRolesByIds(Set<Integer> roleIds);

    List<Role> getAllRoles();

    Role updateRole(Role role);

    void deleteRole(Integer roleId);
    
    Role addPermissionToRole(Integer roleId, Integer permissionId);
}
