package com.ssafy.ssafit.model.dao;

import com.ssafy.ssafit.model.dto.User;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

public interface UserDao {

    int join(User user);
//    int loginCheck(String id);
    int deleteUser(String id);
    int updateUser(HashMap<String, Object> map);
    User selectUser(String id);
    int overlappedID(String id);
}
