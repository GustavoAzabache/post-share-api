package com.me.post_share_api.controller;

import java.net.URI;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.me.post_share_api.dto.post_dto.PostRequestDTO;
import com.me.post_share_api.dto.post_dto.PostResponseDTO;
import com.me.post_share_api.service.interfaces.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("")
    public ResponseEntity<List<PostResponseDTO>> findAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> findPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PostMapping("")
    public ResponseEntity<PostResponseDTO> createPost(@Valid @RequestBody PostRequestDTO postRequestDTO,
            Principal principal) {
        PostResponseDTO postCreated = postService.createPost(postRequestDTO, principal.getName());

        return ResponseEntity.created(URI.create("/api/posts/" + postCreated.getId())).body(postCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDTO> updatePost(@PathVariable Long id,
            @Valid @RequestBody PostRequestDTO postRequestDTO, Principal principal) {
        PostResponseDTO postUpdated = postService.updatePost(id, postRequestDTO, principal.getName());

        return ResponseEntity.ok(postUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id, Principal principal) {
        postService.deletePost(id, principal.getName());

        return ResponseEntity.noContent().build();
    }
}
