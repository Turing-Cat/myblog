package com.example.myblog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.myblog.pojo.Type;

import java.util.List;

public interface TypeService {
    boolean save(Type type);
    Type getType(Long id);
    List<Type> getAllTyes();
    Type update(Long id,Type type);
    void deleteType(Long id);
    Type findTypeByName(String name);
    List<Type> getBlogType();
}
