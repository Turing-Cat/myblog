package com.example.myblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.myblog.pojo.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CommentMapper extends BaseMapper<Comment> {
    
    List<Comment> findByBlogIdAndParentCommentNull(@Param("blogId") Long blogId,
                                                   @Param("blogParentId")Long blogParentId);
    
}
