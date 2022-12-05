package com.ssafy.ssafit.model.service;

import com.ssafy.ssafit.model.dao.UserDao;
import com.ssafy.ssafit.model.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserDao userDao;


    @Override
    public User getUser(String id) {
        return userDao.selectUser(id);
    }

    @Transactional
    @Override
    public int updateUser(HashMap<String, Object> m) {
        return userDao.updateUser(m);
    }

    @Transactional
    @Override
    public int quit(String id) {
        return userDao.deleteUser(id);
    }

    @Transactional
    @Override
    public int join(User user) {
        return userDao.join(user);
    }

    @Override
    public int loginCheck(String id, String password) {
        User user = null;
        try {
            user = userDao.selectUser(id);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

        if (user!=null && user.getUserPassword().equals(password)) {
            return 1;
        }
        return 0;
    }

    @Override
    public int overlappedID(String id) {
        return userDao.overlappedID(id);
    }

}
