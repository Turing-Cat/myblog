package com.example.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.myblog.mapper.CommentMapper;
import com.example.myblog.pojo.Comment;
import com.example.myblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    
    @Override
    public List<Comment> getCommentByBlogId(Long blogid) {
        List<Comment> comments = commentMapper.findByBlogIdAndParentCommentNull(blogid, -1L);
        return comments;
    }
    
    @Override
    public int saveComment(Comment comment) {
        Long parentCommentId = comment.getParentComment().getId();
        if (parentCommentId==-1){
            comment.setParentCommentId(-1L);
            comment.setParentComment(null);
        }else {
            QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<>();
            commentQueryWrapper.eq("parent_comment_id",comment.getParentCommentId());
            comment.setParentComment(commentMapper.selectOne(commentQueryWrapper));
        }
        comment.setCreateTime(new Date());
        return commentMapper.insert(comment);
    }
}
