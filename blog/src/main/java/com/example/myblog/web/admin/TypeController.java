package com.example.myblog.web.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.myblog.pojo.Type;
import com.example.myblog.service.TypeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(@RequestParam(required = false,defaultValue = "1",value = "pagenum") Integer pagenum,
                        Model model){
        PageHelper.startPage(pagenum, 5);
        List<Type> allTyes = typeService.getAllTyes();
        for (Type type:allTyes){
            System.out.println(type);
        }
        PageInfo<Type> pageInfo = new PageInfo<>(allTyes);
        model.addAttribute("pageInfo",pageInfo);
        //System.out.println(pageInfo.getPages()+"---"+pageInfo.getCurrent()+"---"+pageInfo.getTotal());
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String toAddType(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String addType(Type type, RedirectAttributes redirectAttributes){
        Type t = typeService.findTypeByName(type.getName());
        if(t!=null){
            redirectAttributes.addAttribute("message","不能添加重复的分类");
            return "redirect:/admin/types/input";
        }else {
            redirectAttributes.addAttribute("message","添加成功");
        }
        typeService.save(type);
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/input")
    public String toEditType(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }


    @PostMapping("/types/{id}")
    public String editType(@PathVariable Long id,Type type,RedirectAttributes attributes){
        Type t = typeService.findTypeByName(type.getName());
        if(t != null){
            attributes.addFlashAttribute("message", "不能添加重复的分类");
            return "redirect:/admin/types/input";
        }else {
            attributes.addFlashAttribute("message", "修改成功");
        }
        typeService.update(id,type);
        return "redirect:/admin/types";   //不能直接跳转到types页面，否则不会显示type数据(没经过types方法)
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }

}
