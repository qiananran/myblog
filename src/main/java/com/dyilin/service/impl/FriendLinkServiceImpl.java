package com.dyilin.service.impl;

import com.dyilin.NotFoundException;
import com.dyilin.dao.FriendLinkRespository;
import com.dyilin.pojo.FriendLink;
import com.dyilin.service.FriendLinkService;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * @program: myblog
 * @Author: Dyilin
 * @Description:
 * @create: 2020-12-08 22:21
 */
@Service
public class FriendLinkServiceImpl implements FriendLinkService {

    @Autowired
    private FriendLinkRespository friendLinkRespository;

    @Transactional
    @Override
    public List<FriendLink> listFriendlinks() {
        return friendLinkRespository.findAll();
    }

    @Transactional
    @Override
    public FriendLink saveFriendLink(FriendLink friendLink) {
        return friendLinkRespository.save(friendLink);
    }

    @Transactional
    @Override
    public FriendLink getFriendLink(Long id) {
        return friendLinkRespository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Page<FriendLink> listFriendLink(Pageable pageable) {
//        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        return friendLinkRespository.findAll(pageable);
    }

    @Transactional
    @Override
    public FriendLink updateFriendLink(Long id, FriendLink friendLink) {
        FriendLink F = friendLinkRespository.findById(id).orElse(null);
        if(F == null){
            throw new NotFoundException("不存在该友链");
        }
        BeanUtils.copyProperties(friendLink,F);
        return friendLinkRespository.save(F);
    }

    @Transactional
    @Override
    public void deleteFriendLink(Long id) {
        friendLinkRespository.deleteById(id);
    }

}
