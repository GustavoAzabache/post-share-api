package com.me.post_share_api.config;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.me.post_share_api.dto.user_dto.UserRequestDTO;
import com.me.post_share_api.dto.user_dto.UserResponseDTO;
import com.me.post_share_api.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "posts", ignore = true)
    UserEntity toEntity(UserRequestDTO userRequestDTO);

    @Mapping(source = "role.name", target = "roleName")
    UserResponseDTO toResponseDTO(UserEntity userEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "posts", ignore = true)
    void updateEntityFromDTO(UserRequestDTO userRequestDTO, @MappingTarget UserEntity userEntity);

}
