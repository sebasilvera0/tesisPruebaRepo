package com.inavi.backend.user.service;

import com.inavi.backend.user.model.Permission;

import java.util.List;
import java.util.Set;

public interface PermissionService {
  
    Permission getPermissionById(Integer permissionId);
    
    Permission createPermission(Permission permission);
    
    Set<Permission> getPermisionByIds(Set<Integer> roleIds);

}
