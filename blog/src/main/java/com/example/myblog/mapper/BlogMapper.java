package com.example.myblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.myblog.pojo.Blog;
import com.example.myblog.pojo.BlogAndTag;

import java.util.List;

public interface BlogMapper extends BaseMapper<Blog> {
    List<Blog> getAllBlogs();
    
    Blog getDetailedBlog(Long id);
    
    List<Blog> getByTypeId(Long typeId);
    
    List<Blog> getByTagId(Long tagId);
    
    List<Blog> getIndexBlog();//主博客展示
    
    List<Blog> getSearchBlog(String query);
    
    List<Blog> searchAllBlog(Blog blog);
    
    List<String> findGroupYear();//查询所有年份，返回一个集合
    
    List<Blog> findByYear(String year);//按照年份查询博客
    
    int saveBlogAndTag(BlogAndTag blogAndTag);
}
