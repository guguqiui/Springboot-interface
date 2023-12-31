package cn.edu.bupt.springsecurity.userdetails.demo.controller;

import cn.edu.bupt.springsecurity.userdetails.demo.entity.Users;
import cn.edu.bupt.springsecurity.userdetails.demo.mapper.AuthoritiesMapper;
import cn.edu.bupt.springsecurity.userdetails.demo.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    UsersMapper usersMapper;

    @GetMapping("/login")
    String login(Model model) {
        return "user/login";
    }

    @GetMapping("/register")
    String register() {
        return "user/register";
    }

    @PostMapping("/register")
    String registerDone(Model model, String username, String password) {
        Users users = usersMapper.selectById(username);
        if (users != null) {
            model.addAttribute("msg", "用户名已存在！");
            return "user/register";
        } else {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            users = new Users(username, passwordEncoder.encode(password), true);
            usersMapper.insert(users);
            return "redirect:/user/login";
        }
    }
}
