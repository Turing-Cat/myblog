package com.example.myblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.myblog.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface TagMapper extends BaseMapper<Tag> {
    List<Tag> getBlogTag();//展示博客首页标签
}
