package com.synerzip.spring.service.impl;

import com.synerzip.spring.exception.ResourceNotFoundException;
import com.synerzip.spring.model.Comment;
import com.synerzip.spring.model.Post;
import com.synerzip.spring.repository.CommentRepository;
import com.synerzip.spring.repository.PostRepository;
import com.synerzip.spring.service.CommentService;
import com.synerzip.spring.service.PostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PostCommentServiceImpl implements PostService, CommentService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;


    @Override
    public Page<Comment> getAllCommentsByPostId(Long postId, int page, int size) {
        Pageable pageable =  PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        return commentRepository.findByPostId(postId, pageable);
    }

    @Override
    public Comment createComment(Long postId, Comment comment) {
        return postRepository.findById(postId).map(post -> {
            comment.setPost(post);
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("Post not found with the id: " + postId));
    }

    @Override
    public Comment updateComment(Long commentId, Long postId, Comment comment) {
        if (!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("Post not found with the id: " + postId);
        }

        return commentRepository.findByIdAndPostId(commentId, postId).map(comment1 -> {
            comment1.setText(comment.getText());
            return commentRepository.save(comment1);
        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with the id: " + commentId));
    }

    @Override
    public void deleteComment(Long commentId, Long postId) {
        if (!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("Post not found with the id: " + postId);
        }
        Comment comment = commentRepository.findByIdAndPostId(commentId, postId).orElseThrow(() -> new ResourceNotFoundException("Comment not found with the id: " + commentId));
        commentRepository.delete(comment);
    }

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Long postId, Post newPost) {
        return postRepository.findById(postId).map(post -> {
            BeanUtils.copyProperties(newPost,post);
            return postRepository.save(post);
        }).orElseThrow(()->new ResourceNotFoundException("Post not found with the id: " + postId));
    }

    @Override
    public Post getPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post not found with the id: " + postId));
    }

    @Override
    public void deletePost(Long postId) {
        Post post=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post not found with the id: " + postId));
        postRepository.delete(post);
    }

    @Override
    public Page<Post> getAllPosts(int page,int size) {
        Pageable pageable = PageRequest.of(page,size,Sort.Direction.DESC, "createdAt");
        return postRepository.findAll(pageable);
    }
}
