/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inavi.backend.user.service;

/**
 *
 * @author sebas
 */
import com.inavi.backend.user.dto.in.UserDtoIn;
import com.inavi.backend.user.exception.NotFound;
import com.inavi.backend.user.mapper.UserMapper;
import com.inavi.backend.user.model.User;
import com.inavi.backend.user.repository.ProductiveUnitRepository;
import com.inavi.backend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ValidationService validationService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final ProductiveUnitRepository productUnitRepository;
    

     @Autowired
    public UserServiceImpl(UserRepository userRepository, ValidationService validationService, PasswordEncoder passwordEncoder, UserMapper userMapper
    ,ProductiveUnitRepository productUnitRepository) {
        this.userRepository = userRepository;
        this.validationService = validationService;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder; // Spring inyecta la instancia creada en SecurityConfig
        this.productUnitRepository = productUnitRepository;
    }
    
   

    @Override
    public User registerUser(UserDtoIn userDtoIn) {
        
        Set<Role> roles = roleService.getRolesByIds(userDtoIn.getRoleIds());
        
        ProductiveUnit product = productUnitRepository(userDtoIn.getProductiveUnit());
        
        User user = userMapper.dtoToUser(userDtoIn);
        user.setRoles(roles);
        
        User user = userMapper.dtoToUser(userDtoIn);
        validationService.validateUser(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    
    public User login(String email, String rawPassword) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new NotFound("User", email);
        }
        
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new NotFound("User", email); 
        }

        return user;
    }

    
    @Override
    public User getUserById(Integer userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new NotFound("User",  userId  ));
    }

   

    @Override
    public User updateUser(DtoUser user) {
        
        
        
        this.getUserById(user.getId());
        
        validationService.validateUser(user);
        
        return userRepository.save(user);
    }

    

    


}
