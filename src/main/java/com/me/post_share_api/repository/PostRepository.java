package com.me.post_share_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.me.post_share_api.entity.PostEntity;
import com.me.post_share_api.entity.UserEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

    List<PostEntity> findByUser(UserEntity user);
}
