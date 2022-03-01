package com.dyilin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: myblog
 * @Author: Dyilin
 * @Description:
 * @create: 2020-12-11 10:28
 */
@Controller
public class AboutController {

    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
