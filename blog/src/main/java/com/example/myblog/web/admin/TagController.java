package com.example.myblog.web.admin;

import com.example.myblog.pojo.Tag;
import com.example.myblog.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagService tagService;
    
    @GetMapping("/tags")
    public String tags(@RequestParam(required = false, defaultValue = "1", value = "pagenum") int pagenum,
                       Model model) {
        PageHelper.startPage(pagenum, 5);
        List<Tag> allTag = tagService.getAllTag();
        PageInfo<Tag> pageInfo = new PageInfo<>(allTag);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/tags";
    }
    
    @GetMapping("/tags/input")
    public String toAddTag(Model model) {
        model.addAttribute("tag", new Tag());
        return "admin/tags-input";
    }
    
    @GetMapping("/tags/{id}/input")
    public String toEditTag(@PathVariable Long id, Model model) {
        model.addAttribute("tag", tagService.getTage(id));
        return "admin/tags-input";
    }
    
    @PostMapping("/tags")
    public String addTag(Tag tag, RedirectAttributes redirectAttributes) {
        Tag t = tagService.getTagByName(tag.getName());
        if(t==null){
            redirectAttributes.addFlashAttribute("message","不能添加重复的标签");
        }else {
            redirectAttributes.addFlashAttribute("message","添加成功");
        }
        tagService.saveTage(tag);
        return  "redirect:/admin/tags";
    }
    
    @PostMapping("/tags/{id}")
    public String editTag(@PathVariable long id,Tag tag,RedirectAttributes attributes){
        Tag t = tagService.getTagByName(tag.getName());
        if(t==null){
            attributes.addAttribute("message","添加成功");
        }else {
            attributes.addAttribute("message","不能呢过添加重复的标签");
            return "redirect:/admin/tags/input";
        }
        tagService.updateTag(tag);
        return "redirect:/admin/tags";
    }
    
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable long id,RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addAttribute("message","删除成功");
        return "redirect:/admin/tags";
    }
}
