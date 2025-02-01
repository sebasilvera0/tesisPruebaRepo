/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inavi.backend.user.mapper;


import com.inavi.backend.user.dto.in.UserDtoIn;
import com.inavi.backend.user.dto.in.UserUpdateDto;
import com.inavi.backend.user.model.User;
import com.inavi.backend.user.model.Role;
import com.inavi.backend.user.repository.RoleRepository;  // El repositorio de roles
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {

    public User dtoToUser(UserDtoIn dto) {
        User user = new User();
        user.setSurname(dto.getSurname());
        user.setPassword(dto.getPassword());
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setNationalIdentification(dto.getNationalIdentification());
        user.setEmail(dto.getEmail());
        return user;  // No asignamos roles aquí, se validan en el servicio
    }

    public UserDtoIn userToDto(User user) {
        UserDtoIn dtoUser = new UserDtoIn();
        dtoUser.setSurname(user.getSurname());
        dtoUser.setPassword(user.getPassword());
        dtoUser.setName(user.getName());
        dtoUser.setSurname(user.getSurname());
        dtoUser.setNationalIdentification(user.getNationalIdentification());
        dtoUser.setEmail(user.getEmail());
        return dtoUser;
    }
    
    public UserDtoIn updateDtoToDtoIn(UserUpdateDto updateDto) {
        UserDtoIn dto = new UserDtoIn();
        dto.setName(updateDto.getName());
        dto.setSurname(updateDto.getSurname());
        dto.setNationalIdentification(updateDto.getNationalIdentification());
        dto.setEmail(updateDto.getEmail());
        dto.setRoles(updateDto.getRoles());
        
        if (updateDto.getPassword() != null && !updateDto.getPassword().isEmpty()) {
            dto.setPassword(updateDto.getPassword());
        }
        dto.setProductiveUnit(updateDto.getProductiveUnit());
        dto.setVineyardAccess(updateDto.getVineyardAccess());
        return dto;
    }
}


