package com.example.myblog;

import com.example.myblog.pojo.Blog;
import com.example.myblog.pojo.Type;
import com.example.myblog.pojo.User;
import com.example.myblog.service.BlogService;
import com.example.myblog.service.TypeService;
import com.example.myblog.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class MyblogApplicationTests {
    @Autowired
    BlogService blogService;
    @Autowired
    UserService userService;
    @Autowired
    TypeService typeService;
    @Test
    void contextLoads() {
    }


    @Test
    public void testLogin(){
        User user = new User();
        user.setUsername("zjh");
        user.setPassword("123456");
        User user1 = userService.checkUser(user);
        System.out.println(user1);
    }

    @Test
    public void testPage(){
        PageHelper.startPage(1, 3);
        List<Type> allTyes = typeService.getAllTyes();
        PageInfo<Type> pageInfo = new PageInfo<>(allTyes);
        System.out.println(pageInfo);
    }
    
    @Test
    public void test(){
    
    }
}
