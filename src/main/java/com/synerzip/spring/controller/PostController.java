package com.synerzip.spring.controller;


import com.synerzip.spring.model.Post;
import com.synerzip.spring.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    PostService postService;


    @GetMapping("/get/all")
    Page<Post> getAllPosts(@RequestParam("page") int page,@RequestParam("size") int size){
        return postService.getAllPosts(page,size);
    }

    @PostMapping("/add")
    Post createNewPost(@Valid @RequestBody Post post){
        return postService.createPost(post);
    }

    @GetMapping("/get/{postId}")
    Post getPostById(@PathVariable(name = "postId") Long postId){
        return postService.getPost(postId);
    }

    @PutMapping("/update/{postId}")
    Post updatePost(@PathVariable(name = "postId") Long postId,@Valid @RequestBody Post post){
        return postService.updatePost(postId,post);
    }

    @DeleteMapping("/delete/{postId}")
    ResponseEntity<?> deletePostById(@PathVariable(name = "postId") Long postId){
        postService.deletePost(postId);
        return ResponseEntity.ok().build();
    }
}
