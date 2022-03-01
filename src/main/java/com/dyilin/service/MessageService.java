package com.dyilin.service;

import com.dyilin.pojo.Message;

import java.util.List;

public interface MessageService {

    List<Message> listMessage();
    Message saveMessage(Message message);

}
