package com.synerzip.spring.controller;

import com.synerzip.spring.model.Comment;
import com.synerzip.spring.service.CommentService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/comments")
public class CommentsController {

    @Autowired
    CommentService commentService;

    @GetMapping("/get/all/{postId}")
    Page<Comment> getAllCommentsByPostId(@PathVariable(value = "postId") Long postId, @RequestParam("page") int page,@RequestParam("size") int size){
        return commentService.getAllCommentsByPostId(postId,page,size);
    }

    @PostMapping("/create/{postId}")
    Comment postNewComment(@PathVariable("postId") Long postId, @Valid @RequestBody Comment comment){
        return commentService.createComment(postId,comment);
    }

    @PutMapping("/update/{postId}/{commentId}")
    Comment updateCommentByCommentIdAndPostId(@PathVariable("postId") Long postId,@PathVariable("commentId") Long commentId,@Valid @RequestBody Comment comment){
        return commentService.updateComment(commentId,postId,comment);
    }

    @DeleteMapping("/delete/{postId}/{commentId}")
    ResponseEntity<?> deleteCommentByCommentIdAndPostId(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId){
        commentService.deleteComment(commentId,postId);
        return ResponseEntity.ok().build();
    }



}
