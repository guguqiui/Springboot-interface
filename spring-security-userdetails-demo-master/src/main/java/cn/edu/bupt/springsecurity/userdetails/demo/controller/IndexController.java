package cn.edu.bupt.springsecurity.userdetails.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    String index(Model model, Authentication auth){

        model.addAttribute("username",auth.getName());
        model.addAttribute("principal",auth.getPrincipal().toString());
        model.addAttribute("authorities",auth.getAuthorities().toString());

        return "index";
    }

    @GetMapping("/admin")
    String admin(){

        return "admin";
    }
}
