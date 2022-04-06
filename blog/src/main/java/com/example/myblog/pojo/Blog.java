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
@TableName("t_blog")
public class Blog {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    
    private String title;
    private String content;
    private String firstPicture;
    private String flag;
    private Integer views;
    private Boolean appreciation;//是否开启赞赏
    private Boolean shareStatement;//是否开启转载声明
    private Boolean commentTabled;//评论列表是否开启
    private Boolean published;//是否发布
    private Boolean recommend;//是否推荐
    private Date createTime;
    private Date updateTime;
    
    
    @TableField(exist = false)
    private Type type;

    @TableField(exist = false)
    private List<Tag> tags = new ArrayList<>();

    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private List<Comment> comments = new ArrayList<>();
    
    private Long typeId;
    private Long userId;
    private String description;
    private String tagIds;
    
    public void init(){
        this.tagIds = tagsToIds(this.getTags());
    }
    
    //将tags集合转换为tagIds字符串形式：“1,2,3”,用于编辑博客时显示博客的tag
    private String tagsToIds(List<Tag> tags){
        if(!tags.isEmpty()){
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for(Tag tag: tags){
                if(flag){
                    ids.append(",");
                }else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        }else {
            return tagIds;
        }
    }

}
