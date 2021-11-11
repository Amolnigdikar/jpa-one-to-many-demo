package com.synerzip.spring.service;

import com.synerzip.spring.model.Comment;
import org.springframework.data.domain.Page;

public interface CommentService {

    Page<Comment> getAllCommentsByPostId(Long postId,int page,int size);

    Comment createComment(Long postId, Comment comment);

    Comment updateComment(Long commentId,Long postId,Comment comment);

    void deleteComment(Long commentId,Long postId);
}
