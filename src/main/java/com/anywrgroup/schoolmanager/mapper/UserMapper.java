package com.anywrgroup.schoolmanager.mapper;


import com.anywrgroup.schoolmanager.dto.UserDTO;
import com.anywrgroup.schoolmanager.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDTO userDTO);

    UserDTO toDto(User user);
}
