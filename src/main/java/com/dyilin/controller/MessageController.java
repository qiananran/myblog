package com.dyilin.controller;

import com.dyilin.pojo.Message;
import com.dyilin.pojo.User;
import com.dyilin.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * @program: myblog
 * @Author: Dyilin
 * @Description:
 * @create: 2020-12-10 14:15
 */
@Controller
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Value("${message.avatar}")
    private String avatar;

    //    显示留言页面
    @GetMapping("/message")
    public String message() {
        return "message";
    }


    @GetMapping("/messagecomment")
    public String messageComment(Model model){
        model.addAttribute("messages",messageService.listMessage());
        return "message::messageList";
    }

    @PostMapping("/message")
    public String post(Message message, HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user != null) {
            message.setAvatar(user.getAvatar());
            message.setAdminComment(true);
        } else {
            message.setAvatar(avatar);
        }
        messageService.saveMessage(message);
        return "redirect:/messagecomment";
    }
}
