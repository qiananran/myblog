package com.dyilin.dao;

import com.dyilin.pojo.FriendLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendLinkRespository extends JpaRepository<FriendLink,Long> {

    FriendLink findByBlogname(String blogname);
}
