package com.me.post_share_api.config;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.me.post_share_api.dto.post_dto.PostRequestDTO;
import com.me.post_share_api.dto.post_dto.PostResponseDTO;
import com.me.post_share_api.entity.PostEntity;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    PostEntity toEntity(PostRequestDTO postRequestDTO);

    @Mapping(source = "user.id", target = "userId")
    PostResponseDTO toResponseDTO(PostEntity postEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateEntityFromDTO(PostRequestDTO postRequestDTO, @MappingTarget PostEntity postEntity);

}
