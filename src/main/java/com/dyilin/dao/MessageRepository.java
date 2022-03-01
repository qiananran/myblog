package com.dyilin.dao;

import com.dyilin.pojo.Message;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository  extends JpaRepository<Message, Long> {

    List<Message> findAllByParentMessageNull(Sort Sort);

}
