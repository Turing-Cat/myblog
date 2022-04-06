package com.example.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.myblog.exception.NotFoundException;
import com.example.myblog.mapper.BlogMapper;
import com.example.myblog.pojo.Blog;
import com.example.myblog.pojo.Tag;
import com.example.myblog.service.BlogService;
import com.example.myblog.pojo.BlogAndTag;
import com.example.myblog.util.MarkdownUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;
    @Override
    public Blog getBlog(Long id) {
        return blogMapper.selectById(id);
    }

    @Override
    public List<Blog> getAllBlogs() {
        return blogMapper.getAllBlogs();
    }

    @Override
    @Transactional
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        blogMapper.insert(blog);
        Long id = blog.getId();
        List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag = null;
        for (Tag tag:tags){
            blogAndTag =new BlogAndTag(tag.getId(),id);
            blogMapper.saveBlogAndTag(blogAndTag);
        }
        return 1;
    }
    
    @Override
    @Transactional
    public int updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        List<Tag> tags =blog.getTags();
        BlogAndTag blogAndTag = null;
        for (Tag tag:tags){
            blogAndTag = new BlogAndTag(tag.getId(),blog.getId());
            blogMapper.saveBlogAndTag(blogAndTag);
        }
        return blogMapper.updateById(blog);
    }
    
    @Override
    @Transactional
    public int deleteBlog(Long id) {
        return blogMapper.deleteById(id);
    }
    
    @Override
    public Blog getDetailedBlog(Long id) {
        Blog blog =  blogMapper.getDetailedBlog(id);
        if (blog==null){
            throw new NotFoundException("该博客不存在");
        }
        String content = blog.getContent();
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        return blog;
    }
    
    @Override
    public List<Blog> getByTypeId(Long typeId) {
        return blogMapper.getByTypeId(typeId);
    }
    
    @Override
    public List<Blog> getByTagId(Long tagId) {
        return blogMapper.getByTagId(tagId);
    }
    
    @Override
    public List<Blog> getIndexBlog() {
        return blogMapper.getIndexBlog();
    }
    
    @Override
    public List<Blog> getSearchBlog(String query) {
        return blogMapper.getSearchBlog(query);
    }
    
    @Override
    public List<Blog> searchAllBlog(Blog blog) {
        return blogMapper.searchAllBlog(blog);
    }
    
    
    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> groupYear = blogMapper.findGroupYear();
        Set<String> blogs = new HashSet<>(groupYear);//去重
        Map<String,List<Blog>> map = new HashMap<>();
        for (String year:blogs){
            map.put(year,blogMapper.findByYear(year));
        }
        return map;
    }
    
    @Override
    public int countBlog() {
        return blogMapper.getAllBlogs().size();
    }
    
    @Override
    public  List<Blog> getAllRecommendBlog(){
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","title","recommend");
        queryWrapper.orderByDesc("update_time");
        return blogMapper.selectList(queryWrapper);
    }
}
