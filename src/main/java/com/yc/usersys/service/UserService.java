package com.yc.usersys.service;

import com.yc.usersys.dao.UserDAO;
import com.yc.usersys.entity.User;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    private UserDAO dao =new UserDAO();


    public int add(User user){
        try {
            return dao.add(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public Map<String,Object> login(String uname,String upwd){
        Map<String,Object> map =new HashMap<>();
        map.put("uname",uname);
        map.put("upwd",upwd);
        map.put("state",1);
        try {
            List<Map<String,Object>> list =dao.findMap(map);
            if(null != list&& !list.isEmpty()){
                return list.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public User findTel(User user){
        if(null ==user.getTel() || "".equals(user.getTel())){
            return null;
        }
        try {
            List<User> list = null;
            list = dao.finds(user);
            if (null == list || list.isEmpty()) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list.get(0);
    }
}
