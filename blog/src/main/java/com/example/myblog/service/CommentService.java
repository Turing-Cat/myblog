package com.example.myblog.service;

import com.example.myblog.pojo.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentByBlogId(Long blogid);
    int saveComment(Comment comment);
}
