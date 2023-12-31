package cn.edu.bupt.springsecurity.userdetails.demo.controller;

import cn.edu.bupt.springsecurity.userdetails.demo.service.MyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class RequestController {

    private final MyService myService;

    @Autowired
    public RequestController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/api")
    public String getResponseFromApi() {
        return myService.getResponseFromApi();
    }
}

