package com.synerzip.spring.service;

import com.synerzip.spring.model.Post;
import org.springframework.data.domain.Page;

public interface PostService {


    Post createPost(Post post);

    Post updatePost(Long postId, Post post);

    Post getPost(Long postId);

    void deletePost(Long postId);

    Page<Post> getAllPosts(int page, int size);
}
