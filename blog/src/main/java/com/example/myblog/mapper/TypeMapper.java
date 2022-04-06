package com.example.myblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.myblog.pojo.Type;

import java.util.List;

public interface TypeMapper extends BaseMapper<Type> {
    List<Type> getBlogType();
}
