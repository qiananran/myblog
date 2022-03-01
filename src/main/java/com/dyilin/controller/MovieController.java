package com.dyilin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: myblog
 * @Author: Dyilin
 * @Description:
 * @create: 2020-12-10 16:42
 */
@Controller
public class MovieController {

    @GetMapping("/movie")
    public String movie(){
        return "movie";
    }
}
