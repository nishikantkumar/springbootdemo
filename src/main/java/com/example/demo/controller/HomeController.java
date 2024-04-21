package com.example.demo.controller;


import com.example.demo.service.AppInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private AppInfoService appInfoService;

    @GetMapping("/")
    public String getHome(Model model) {
        model.addAttribute("appInfo", appInfoService.getAppInfo());
        return "home";   // refers to classpath resource /templates/home.html
    }
}
