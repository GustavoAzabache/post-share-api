package com.me.post_share_api.service.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.me.post_share_api.config.UserMapper;
import com.me.post_share_api.dto.user_dto.UserRequestDTO;
import com.me.post_share_api.dto.user_dto.UserResponseDTO;
import com.me.post_share_api.entity.RoleEntity;
import com.me.post_share_api.entity.UserEntity;
import com.me.post_share_api.entity.enums.RoleEnum;
import com.me.post_share_api.exception.RoleNotFoundException;
import com.me.post_share_api.exception.UserNotFoundException;
import com.me.post_share_api.exception.UsernameAlreadyUsedException;
import com.me.post_share_api.repository.RoleRepository;
import com.me.post_share_api.repository.UserRepository;
import com.me.post_share_api.service.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        UserEntity userFound = findUser(id);

        return userMapper.toResponseDTO(userFound);
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        if (userRepository.existsByUsername(userRequestDTO.getUsername())) {
            throw new UsernameAlreadyUsedException("El username ingresado ya está siendo usado.");
        }

        UserEntity userCreated = userMapper.toEntity(userRequestDTO);
        RoleEntity roleFound = roleRepository.findByName(RoleEnum.ROLE_USER)
                .orElseThrow(() -> new RoleNotFoundException("El rol no ha sido encontrado"));

        userCreated.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        userCreated.setRole(roleFound);
        userRepository.save(userCreated);

        return userMapper.toResponseDTO(userCreated);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        UserEntity userFound = findUser(id);

        // El username del usuario a actualizar, es igual al username del Request??
        // O mejor dicho, el usuario ha mandando un username diferente al que tiene??
        // Como es una AND, con tal de que no cumpla esa, no entrará al if
        // Si es diferente, se evalua ahora: el username enviado en el Requeste ya
        // existe??
        if (!userFound.getUsername().equals(userRequestDTO.getUsername())
                && userRepository.existsByUsername(userRequestDTO.getUsername())) {
            throw new UsernameAlreadyUsedException("El username ingresado ya está siendo usado.");
        }

        userMapper.updateEntityFromDTO(userRequestDTO, userFound);
        userRepository.save(userFound);

        return userMapper.toResponseDTO(userFound);
    }

    @Override
    public void deleteUser(Long id) {
        UserEntity userFound = findUser(id);

        userRepository.delete(userFound);
    }

    private UserEntity findUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("El usuario con ID: " + id + " no ha sido encontrado."));
    }

}
