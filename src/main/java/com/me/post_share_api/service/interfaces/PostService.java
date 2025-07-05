package com.me.post_share_api.service.interfaces;

import java.util.List;

import com.me.post_share_api.dto.post_dto.PostRequestDTO;
import com.me.post_share_api.dto.post_dto.PostResponseDTO;

public interface PostService {

    List<PostResponseDTO> getAllPosts();

    PostResponseDTO getPostById(Long id);

    PostResponseDTO createPost(PostRequestDTO postRequestDTO, String username);

    PostResponseDTO updatePost(Long id, PostRequestDTO postRequestDTO, String username);

    void deletePost(Long id, String username);

    List<PostResponseDTO> getPostsByUsername(String username);
}
