package com.dyilin.service.impl;

import com.dyilin.dao.MessageRepository;
import com.dyilin.pojo.Message;
import com.dyilin.service.MessageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: myblog
 * @Author: Dyilin
 * @Description:
 * @create: 2020-12-10 14:11
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<Message> listMessage() {
        final Sort sort = Sort.by(Sort.Direction.DESC,"createTime");
        List<Message> messages = messageRepository.findAllByParentMessageNull(sort);
        return eachComment(messages);
    }

    @Transactional
    @Override
    public Message saveMessage(Message message) {

        Long parentMessageId = message.getParentMessage().getId();
        if (parentMessageId != -1) {
            message.setParentMessage(messageRepository.findById(parentMessageId).orElse(null));
        } else {
            message.setParentMessage(null);
        }
        message.setCreateTime(new Date());
        return messageRepository.save(message);
    }

    /**
     * 循环每个顶级的评论节点
     * @param messages
     * @return
     */
    private List<Message> eachComment(List<Message> messages) {
        List<Message> commentsView = new ArrayList<>();
        for (Message message : messages) {
            Message c = new Message();
            BeanUtils.copyProperties(message,c);
            commentsView.add(c);
        }
        //合并评论的各层子代到第一级子代集合中
        combineChildren(commentsView);
        return commentsView;
    }

    /**
     *
     * @param messages root根节点，blog不为空的对象集合
     * @return
     */
    private void combineChildren(List<Message> messages) {
        for (Message message : messages) {
            List<Message> replys1 = message.getReplyMessages();
            for(Message reply1 : replys1) {
                //循环迭代，找出子代，存放在tempReplys中
                recursively(reply1);
            }
            //修改顶级节点的reply集合为迭代处理后的集合
            message.setReplyMessages(tempReplys);
            //清除临时存放区
            tempReplys = new ArrayList<>();
        }
    }

    //存放迭代找出的所有子代的集合
    private List<Message> tempReplys = new ArrayList<>();
    /**
     * 递归迭代，剥洋葱
     * @param message 被迭代的对象
     * @return
     */
    private void recursively(Message message) {
        tempReplys.add(message);//顶节点添加到临时存放集合
        if (message.getReplyMessages().size()>0) {
            List<Message> replys = message.getReplyMessages();
            for (Message reply : replys) {
                tempReplys.add(reply);
                if (reply.getReplyMessages().size()>0) {
                    recursively(reply);
                }
            }
        }
    }
}
