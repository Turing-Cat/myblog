package com.example.myblog.service;

import com.example.myblog.pojo.Blog;

import java.util.List;
import java.util.Map;

public interface BlogService {
    Blog getBlog(Long id);
    
    List<Blog> getAllBlogs();
    
    
    Blog getDetailedBlog(Long id);//博客详情
    
    List<Blog> getByTypeId(Long typeId);
    
    List<Blog> getByTagId(Long tagId);//标签id获取博客列表
    
    List<Blog> getIndexBlog();//主页博客展示
    
    List<Blog> getSearchBlog(String query);//全局搜索博客
    
    List<Blog> searchAllBlog(Blog blog);//后台根据标题、分类、推荐搜索博客
    
    Map<String, List<Blog>> archiveBlog();//归档博客
    
    int countBlog();
    
    int saveBlog(Blog blog);
    
    int updateBlog(Blog blog);
    
    int deleteBlog(Long id);
    
    List<Blog> getAllRecommendBlog();
}
