package com.example.myblog.web;

import com.example.myblog.pojo.Blog;
import com.example.myblog.pojo.Type;
import com.example.myblog.service.BlogService;
import com.example.myblog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeShowController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    
    @GetMapping("/types/{id}")
    public String types(@PathVariable Long id, @RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum,
                        Model model){
        PageHelper.startPage(pagenum,100);
        List<Type> blogType = typeService.getBlogType();
        if(id==-1){
            id = blogType.get(0).getId();
        }
        List<Blog> blogs=blogService.getByTypeId(id);
        PageInfo<Blog> pageInfo=new PageInfo<>(blogs);
        model.addAttribute("types",blogType);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("activeTypeId",id);
        return "types";
    }
}
