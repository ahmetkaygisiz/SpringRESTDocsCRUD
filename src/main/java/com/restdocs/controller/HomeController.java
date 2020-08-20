package com.restdocs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class HomeController {

    @RequestMapping("/")
    public String redirectApiPage(){
    	return "redirect:/api/user";
    }
}


