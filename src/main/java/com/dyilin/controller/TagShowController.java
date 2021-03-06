package com.dyilin.controller;

import com.dyilin.bo.TypeOrTagBlog;
import com.dyilin.pojo.Tag;
import com.dyilin.service.BlogService;
import com.dyilin.service.TagService;
import com.dyilin.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.PrivateKey;
import java.util.List;

/**
 * @program: myblog
 * @Author: Dyilin
 * @Description:
 * @create: 2020-12-11 20:12
 */
@Controller
public class TagShowController {

   @Autowired
   private TagService tagService;

   @Autowired
   private BlogService blogService;

   @GetMapping("/tags/{id}")
   public String tags(@PageableDefault(size = 4, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                      @PathVariable Long id, Model model){
       List<TypeOrTagBlog> tags = tagService.listTagBlog();
       if (id == -1) {
           id = tags.get(0).getId().longValue();
       }
       model.addAttribute("tags", tags);
       model.addAttribute("page", blogService.listBlog(id,pageable));
       model.addAttribute("activeTagId", id);
       return "tags";
   }

}
