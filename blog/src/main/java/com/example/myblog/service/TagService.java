package com.example.myblog.service;

import com.example.myblog.pojo.Tag;

import java.util.List;

public interface TagService {
    int saveTage(Tag tag);
    Tag getTage(Long id);
    Tag getTagByName(String name);
    List<Tag> getAllTag();
    List<Tag> getBlogTag();
    List<Tag> getTagByString(String text);
    int updateTag(Tag tag);
    int deleteTag(Long id);
}
