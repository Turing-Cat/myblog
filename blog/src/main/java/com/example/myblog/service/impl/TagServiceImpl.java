package com.example.myblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.myblog.mapper.TagMapper;
import com.example.myblog.pojo.Tag;
import com.example.myblog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Override
    public int saveTage(Tag tag) {
        int insert = tagMapper.insert(tag);
        return insert;
    }

    @Override
    public Tag getTage(Long id) {
        return tagMapper.selectById(id);
    }

    @Override
    public Tag getTagByName(String name) {
        QueryWrapper<Tag> tagQueryWrapper = new QueryWrapper<>();
        tagQueryWrapper.eq("name", name);
        Tag tag = tagMapper.selectOne(tagQueryWrapper);
        return tag;
    }

    @Override
    public List<Tag> getAllTag() {
        return tagMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public List<Tag> getBlogTag() {
        return tagMapper.getBlogTag();
    }

    @Override
    public List<Tag> getTagByString(String text) {
        List<Tag> tags = new ArrayList<>();
        List<Long> longs = convertToList(text);
        for (Long l : longs) {
            tags.add(tagMapper.selectById(l));
        }
        return tags;
    }

    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idArr = ids.split(",");
            for (int i = 0; i < idArr.length; i++)
                list.add(Long.parseLong(idArr[i]));
        }
        return list;
    }

    @Override
    @Transactional
    public int updateTag(Tag tag) {
        return tagMapper.updateById(tag);
    }

    @Override
    public int deleteTag(Long id) {
        return tagMapper.deleteById(id);
    }
}
