/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inavi.backend.user.service;




import com.inavi.backend.user.dto.in.RolDtoIn;
import com.inavi.backend.user.exception.NotFound;
import com.inavi.backend.user.model.Permission;
import com.inavi.backend.user.model.Role;
import com.inavi.backend.user.repository.PermissionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class RoleServiceTest {

    @Autowired
    private RoleServiceImpl roleService;
    
    @Autowired
    private PermissionServiceImpl permissionServiceImpl;

    private RolDtoIn testRole;
    private Permission testPermission;

    @BeforeEach
    void setUp() {
        // Crear un rol de prueba antes de cada test
        testRole = new RolDtoIn();
        testRole.setName("Admin");

        // Crear un permiso de prueba
        testPermission = new Permission();
        testPermission.setId(1);
        testPermission.setNombre("READ_PRIVILEGES");
        
        permissionServiceImpl.createPermission(testPermission);
    }

    @Transactional
    @Test
    void testCreateRole() {
        // Guardar el rol utilizando el servicio
        Role savedRole = roleService.createRole(testRole);

        // Validar que el rol fue guardado correctamente
        assertThat(savedRole).isNotNull();
        assertThat(savedRole.getId()).isEqualTo(savedRole.getId());
        assertThat(savedRole.getName()).isEqualTo(testRole.getName());
    }
    
    @Transactional
    @Test
    void testAddPermissionToRole() {
        // Guardar el rol y permiso
        Role savedRole = roleService.createRole(testRole);
        roleService.addPermissionToRole(savedRole.getId(), testPermission.getId());

        // Verificar que el permiso se añadió correctamente
        Role updatedRole = roleService.getRoleById(savedRole.getId());
        assertThat(updatedRole.getPermissions()).isNotEmpty();
        assertThat(updatedRole.getPermissions()).contains(testPermission);
    }
    
    
    @Transactional
    @Test
    void testGetRoleById_RoleFound() {
        // Guardar el rol y luego buscarlo
        Role savedRole = roleService.createRole(testRole);
        Role foundRole = roleService.getRoleById(savedRole.getId());

        // Validar que el rol encontrado coincide con el rol guardado
        assertThat(foundRole).isNotNull();
        assertThat(foundRole.getId()).isEqualTo(savedRole.getId());
        assertThat(foundRole.getName()).isEqualTo(savedRole.getName());
    }

    @Transactional
    @Test
    void testGetRoleById_RoleNotFound() {
        Integer nonExistentRoleId = 999;

        // Validar que se lanza la excepción al buscar un rol inexistente
        assertThatThrownBy(() -> roleService.getRoleById(nonExistentRoleId))
            .isInstanceOf(NotFound.class)
            .hasMessageContaining("Role with ID " + nonExistentRoleId + " not found");
    }

    @Transactional
    @Test
    void testUpdateRole() {
        // Guardar el rol inicial
        Role savedRole = roleService.createRole(testRole);

        // Actualizar el rol
        savedRole.setName("Updated Admin");
        Role updatedRole = roleService.updateRole(savedRole);

        // Validar que los cambios fueron guardados correctamente
        assertThat(updatedRole).isNotNull();
        assertThat(updatedRole.getId()).isEqualTo(savedRole.getId());
        assertThat(updatedRole.getName()).isEqualTo("Updated Admin");
    }

    @Transactional
    @Test
    void testDeleteRole() {
        // Guardar el rol inicial
        Role savedRole = roleService.createRole(testRole);

        // Eliminar el rol
        roleService.deleteRole(savedRole.getId());

        // Validar que el rol ya no existe
        assertThatThrownBy(() -> roleService.getRoleById(savedRole.getId()))
            .isInstanceOf(NotFound.class)
            .hasMessageContaining("Role with ID " + savedRole.getId() + " not found");
    }
}

    