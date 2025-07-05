package com.me.post_share_api.service.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.post_share_api.config.PostMapper;
import com.me.post_share_api.dto.post_dto.PostRequestDTO;
import com.me.post_share_api.dto.post_dto.PostResponseDTO;
import com.me.post_share_api.entity.PostEntity;
import com.me.post_share_api.entity.UserEntity;
import com.me.post_share_api.exception.PostNotFoundException;
import com.me.post_share_api.exception.UserAccessDeniedExecption;
import com.me.post_share_api.exception.UserNotFoundException;
import com.me.post_share_api.repository.PostRepository;
import com.me.post_share_api.repository.UserRepository;
import com.me.post_share_api.service.interfaces.PostService;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostMapper postMapper;

    @Override
    public List<PostResponseDTO> getAllPosts() {
        return postRepository.findAll()
                .stream().map(postMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PostResponseDTO getPostById(Long id) {
        PostEntity postFound = findPost(id);

        return postMapper.toResponseDTO(postFound);
    }

    @Override
    public PostResponseDTO createPost(PostRequestDTO postRequestDTO, String username) {
        PostEntity postCreated = postMapper.toEntity(postRequestDTO);
        UserEntity userFound = findUser(username);

        postCreated.setUser(userFound);
        postRepository.save(postCreated);

        return postMapper.toResponseDTO(postCreated);
    }

    @Override
    public PostResponseDTO updatePost(Long id, PostRequestDTO postRequestDTO, String username) {
        PostEntity postFound = findPost(id);

        if (!postFound.getUser().getUsername().equals(username)) {
            throw new UserAccessDeniedExecption("No puedes modificar la publicación de otro usuario.");
        }

        if (postFound.getContent().equals(postRequestDTO.getContent())) {
            return postMapper.toResponseDTO(postFound);
        }

        postMapper.updateEntityFromDTO(postRequestDTO, postFound);
        postRepository.save(postFound);

        return postMapper.toResponseDTO(postFound);
    }

    @Override
    public void deletePost(Long id, String username) {
        PostEntity postFound = findPost(id);

        if (!postFound.getUser().getUsername().equals(username)) {
            throw new UserAccessDeniedExecption("No puedes eliminar la publicación de otro usuario.");
        }

        postRepository.delete(postFound);
    }

    @Override
    public List<PostResponseDTO> getPostsByUsername(String username) {
        UserEntity userFound = findUser(username);
        List<PostEntity> posts = postRepository.findByUser(userFound);

        return posts.stream()
                .map(postMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public PostEntity findPost(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("El post con ID: " + id + " no ha sido encontrado."));
    }

    private UserEntity findUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("El usuario " + username + " no ha sido encontrado."));
    }

}
