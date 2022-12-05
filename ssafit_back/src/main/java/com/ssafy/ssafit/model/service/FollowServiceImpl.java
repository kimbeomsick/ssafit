package com.ssafy.ssafit.model.service;

import com.ssafy.ssafit.model.dao.FollowDao;
import com.ssafy.ssafit.model.dto.Follow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FollowServiceImpl implements FollowService{

    @Autowired
    private FollowDao followDao;

    @Override
    public List<String> getFollowerList(String userId) {
        return followDao.selectFollowerList(userId);
    }

    @Override
    public List<String> getFollowingList(String userId) {
        return followDao.selectFollowingList(userId);
    }

    @Transactional
    @Override
    public int removeFollow(Follow follow) {
        return followDao.deleteFollow(follow);
    }

    @Transactional
    @Override
    public int addFollow(Follow follow) {
        return followDao.insertFollow(follow);
    }

    @Override
    public int isFollow(Follow follow) {
        return followDao.isFollow(follow);
    }
}
