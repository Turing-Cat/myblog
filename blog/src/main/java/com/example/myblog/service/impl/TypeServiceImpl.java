package com.example.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.myblog.exception.NotFoundException;
import com.example.myblog.mapper.TypeMapper;
import com.example.myblog.pojo.Type;
import com.example.myblog.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;
    @Override
    @Transactional
    public boolean save(Type type) {
        int insert = typeMapper.insert(type);
        return insert==1;
    }

    @Override
    public Type getType(Long id) {
        return typeMapper.selectById(id);
    }

    @Override
    public List<Type> getAllTyes() {
        List<Type> types = typeMapper.selectList(new QueryWrapper<>());
        return types;
    }


    @Override
    @Transactional
    public Type update(Long id, Type type) {
        Type t = typeMapper.selectById(id);
        if(t==null){
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(type,t);
        UpdateWrapper<Type> typeUpdateWrapper = new UpdateWrapper<>();
        typeUpdateWrapper.eq("id",id);
        typeMapper.update(t, typeUpdateWrapper);
        return t;
    }

    @Override
    @Transactional
    public void deleteType(Long id) {
        typeMapper.deleteById(id);
    }

    @Override
    public Type findTypeByName(String name) {
        QueryWrapper<Type> typeQueryWrapper = new QueryWrapper<>();
        typeQueryWrapper.eq("name",name);
        Type type = typeMapper.selectOne(typeQueryWrapper);
        return type;
    }
    
    @Override
    public List<Type> getBlogType(){
        return typeMapper.getBlogType();
    }
}
