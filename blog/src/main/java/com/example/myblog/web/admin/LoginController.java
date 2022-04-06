package com.example.myblog.web.admin;

import com.example.myblog.pojo.User;
import com.example.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String toLogin(){
        return "admin/login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(User user,
                        HttpSession session,
                        RedirectAttributes attributes){
        User u = userService.checkUser(user);
        if(u!=null){
            session.setAttribute("user",u);
            return "admin/index";
        } else {
            attributes.addFlashAttribute("message","用户名或者密码错误");
            return "redirect:/admin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
