package com.example.myblog.web.admin;

import com.example.myblog.pojo.Blog;
import com.example.myblog.pojo.Tag;
import com.example.myblog.pojo.Type;
import com.example.myblog.pojo.User;
import com.example.myblog.service.BlogService;
import com.example.myblog.service.TagService;
import com.example.myblog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.StringJoiner;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    public void setTypeAndTag(Model model){
        model.addAttribute("types",typeService.getAllTyes());
        model.addAttribute("tags",tagService.getAllTag());
    }
    
    @GetMapping("/blogs")//显示博客列表
    public String blogs(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum,
                        Model model){
        PageHelper.startPage(pagenum,5);
        List<Blog> allBlogs = blogService.getAllBlogs();
        PageInfo<Blog> pageInfo = new PageInfo<>(allBlogs);
        setTypeAndTag(model);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/blogs";
    }


    @PostMapping("/blogs/search")
    public String searchBlogs(Blog blog
                              ,@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum
                              ,Model model) {
        PageHelper.startPage(pagenum, 5);
        List<Blog> allBlogs = blogService.getAllBlogs();
        PageInfo pageInfo = new PageInfo(allBlogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("message", "查询成功");
        setTypeAndTag(model);
        return "admin/blogs";
    }
    
    @GetMapping("/blogs/input")
    public String toAddBlog(Model model){
        model.addAttribute("blog",new Blog());
        setTypeAndTag(model);
        return "admin/blogs-input";
    }
    
    @PostMapping("/blogs")
    public String addOrEditBlog(Blog blog, HttpSession session,RedirectAttributes attributes){
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.getTagByString(blog.getTagIds()));
        blog.setTypeId(blog.getType().getId());
        blog.setUserId(blog.getUser().getId());
        System.out.println(blog);
        if(blog.getId()==null){
            blogService.saveBlog(blog);
        }else {
            blogService.updateBlog(blog);
        }
        attributes.addFlashAttribute("message","操作成功");
        return "redirect:/admin/blogs";
    }
    
    @GetMapping("/blogs/{id}/input")
    public String toEditBlog(@PathVariable Long id,Model model){
        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog",blog);
        setTypeAndTag(model);
        return "admin/blogs-input";
    }
    
    @GetMapping("/blogs/{id}/delete")
    public String deleteBlog(@PathVariable Long id, RedirectAttributes redirectAttributes){
        blogService.deleteBlog(id);
        redirectAttributes.addAttribute("message","删除成功");
        return "redirect:/admin/blogs";
    }
}
