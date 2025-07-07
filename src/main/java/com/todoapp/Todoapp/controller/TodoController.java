package com.todoapp.Todoapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TodoController {


    @GetMapping({"","/","/home"})
    public String showHomePage(){
        return "index";

    }
}
