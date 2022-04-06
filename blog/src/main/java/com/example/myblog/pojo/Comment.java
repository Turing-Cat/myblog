package com.example.myblog.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_comment")
public class Comment {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;//头像
    private Date createTime;
    private boolean adminComment;//是否是管理员评论

    private Long blogId;
    private Long parentCommentId;
    
    @TableField(exist = false)
    private Blog blog;
    @TableField(exist = false)
    private List<Comment> replyComments = new ArrayList<>();
    @TableField(exist = false)
    private Comment parentComment;
}
