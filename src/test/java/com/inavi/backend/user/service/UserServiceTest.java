/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inavi.backend.user.service;


import com.inavi.backend.user.exception.NotFound;
import com.inavi.backend.user.exception.UserNotValid;
import com.inavi.backend.user.model.ProductiveUnit;
import com.inavi.backend.user.model.User;
import com.inavi.backend.user.service.UserServiceImpl;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.transaction.annotation.Transactional;
import com.inavi.backend.user.repository.ProductiveUnitRepository;
import org.hibernate.Hibernate;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserServiceImpl userService;
    
    @Autowired
    private ProductiveUnitRepository productiveUnitRepository; 

    private User testUser;

    @BeforeEach
    void setUp() {
    // Crear y guardar la unidad productiva
    ProductiveUnit productiveUnit = new ProductiveUnit();
    productiveUnit.setId(1);  // Asegúrate de que el ID coincida con el que quieres probar
    productiveUnit.setVineyardIds(List.of(101, 102, 103)); 
    
    // Guardar la ProductiveUnit antes de asociarla con el Usuario
    productiveUnitRepository.save(productiveUnit);  // Asumiendo que tienes un repositorio para ProductiveUnit

    // Crear un usuario de prueba antes de cada test
    testUser = new User();
    testUser.setId(1);
    testUser.setPassword("John Doe");
    testUser.setEmail("john.doe@example.com");
    testUser.setName("John Doe");
    testUser.setSurname("John Doe");
    testUser.setNationalIdentification("1.825.455-1");

    // Asignar la unidad productiva al usuario
    testUser.setProductiveUnit(productiveUnit);
    
    userService.registerUser(testUser);
}
    
    
    @Test
    void testCreateUser() {
        // Llamar al servicio para guardar el usuario
        User savedUser = userService.registerUser(testUser);
        
        ProductiveUnit productiveUnit = savedUser.getProductiveUnit();
        Hibernate.initialize(productiveUnit);

        // Validar que el usuario fue guardado correctamente
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isEqualTo(testUser.getId());
        assertThat(savedUser.getName()).isEqualTo(testUser.getName());
        assertThat(savedUser.getEmail()).isEqualTo(testUser.getEmail());
        assertThat(savedUser.getSurname()).isEqualTo(testUser.getSurname());
        assertThat(savedUser.getNationalIdentification()).isEqualTo(testUser.getNationalIdentification());

        // Validar que la unidad productiva esté asociada correctamente con los IDs de los viñedos
        assertThat(savedUser.getProductiveUnit()).isNotNull();
        assertThat(savedUser.getProductiveUnit().getVineyardIds()).containsExactlyInAnyOrder(101, 102, 103);
    }
    
    
    @Test
    void testInvalidEmailWithUserService() {
        User testUser = new User();
        testUser.setName("ValidName");

        // Caso 1: Email demasiado corto
        testUser.setEmail("a@b");
        assertThatThrownBy(() -> userService.registerUser(testUser))
            .isInstanceOf(UserNotValid.class)
            .hasMessageContaining("Email must be at least 5 characters long");

        // Caso 2: Email sin '@'
        testUser.setEmail("email.com");
        assertThatThrownBy(() -> userService.registerUser(testUser))
            .isInstanceOf(UserNotValid.class)
            .hasMessageContaining("Email must contain '@'");

        // Caso 3: Email sin '.com'
        testUser.setEmail("email@domain");
        assertThatThrownBy(() -> userService.registerUser(testUser))
            .isInstanceOf(UserNotValid.class)
            .hasMessageContaining("Email must end with '.com'");
    }

    
    @Test
    void testGetUserById_UserNotFound() {
        Integer nonExistentUserId = 999;

        // Validar que se lanza la excepción al buscar un usuario inexistente
        assertThatThrownBy(() -> userService.getUserById(nonExistentUserId))
            .isInstanceOf(NotFound.class)
            .hasMessageContaining("User with ID " + nonExistentUserId + " not found");
    }

    
    @Test
    void testGetUserById_UserFound() {
        User savedUser = userService.registerUser(testUser);

        // Luego, intentar recuperar al usuario por su ID
        User retrievedUser = userService.getUserById(savedUser.getId());

        // Validar que el usuario recuperado no es nulo y sus propiedades son correctas
        assertThat(retrievedUser).isNotNull();
        assertThat(retrievedUser.getId()).isEqualTo(savedUser.getId());
        assertThat(retrievedUser.getName()).isEqualTo(savedUser.getName());
        assertThat(retrievedUser.getEmail()).isEqualTo(savedUser.getEmail());
        assertThat(retrievedUser.getSurname()).isEqualTo(savedUser.getSurname());
        assertThat(retrievedUser.getNationalIdentification()).isEqualTo(savedUser.getNationalIdentification());

        // Validar la relación ProductiveUnit y vineyardIds
        assertThat(retrievedUser.getProductiveUnit()).isNotNull();
        assertThat(retrievedUser.getProductiveUnit().getVineyardIds()).containsExactlyInAnyOrder(101, 102, 103);
    }

    
    @Test
    void testUpdateUser_Success() {
        // Actualizar los datos del usuario
        
        User testUser = userService.getUserById(1);
        
        testUser.setName("Jane Doe");
        testUser.setEmail("jane.doe@example.com");
        testUser.setSurname("Smith");
        testUser.setNationalIdentification("1.825.455-2");

        // Llamar al método updateUser
        User updatedUser = userService.updateUser(testUser);

        // Validar que los datos del usuario se han actualizado correctamente
        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getId()).isEqualTo(testUser.getId());
        assertThat(updatedUser.getName()).isEqualTo("Jane Doe");
        assertThat(updatedUser.getEmail()).isEqualTo("jane.doe@example.com");
        assertThat(updatedUser.getSurname()).isEqualTo("Smith");
        assertThat(updatedUser.getNationalIdentification()).isEqualTo("1.825.455-2");
    }

    @Transactional
    @Test
    void testUpdateUser_UserNotFound() {
        // Crear un usuario con un ID inexistente
        User nonExistentUser = new User();
        nonExistentUser.setId(999);
        nonExistentUser.setName("Nonexistent User");
        nonExistentUser.setEmail("nonexistent@example.com");
        nonExistentUser.setSurname("None");
        nonExistentUser.setNationalIdentification("9.999.999-9");

        // Verificar que se lanza la excepción NotFound al intentar actualizar
        assertThatThrownBy(() -> userService.updateUser(nonExistentUser))
            .isInstanceOf(NotFound.class)
            .hasMessageContaining("User with ID 999 not found");
    }
}