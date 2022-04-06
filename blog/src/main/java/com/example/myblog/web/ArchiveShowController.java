package com.example.myblog.web;

import com.example.myblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ArchiveShowController {
    @Autowired
    private BlogService blogService;
    
    @GetMapping("/archives")
    public ModelAndView archives(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("archiveMap",blogService.archiveBlog());
        modelAndView.addObject("blogCount",blogService.countBlog());
        modelAndView.setViewName("archives");
        return modelAndView;
    }
}
