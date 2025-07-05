package com.me.post_share_api.service.interfaces;

import java.util.List;

import com.me.post_share_api.dto.user_dto.UserRequestDTO;
import com.me.post_share_api.dto.user_dto.UserResponseDTO;

public interface UserService {

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO getUserById(Long id);

    UserResponseDTO createUser(UserRequestDTO userRequestDTO);

    UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO);

    void deleteUser(Long id);

}
