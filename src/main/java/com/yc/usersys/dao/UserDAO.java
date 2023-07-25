package com.yc.usersys.dao;

import com.yc.usersys.commons.DbHelper;
import com.yc.usersys.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public class UserDAO {

    private DbHelper db= new DbHelper();

    public int add(User user )throws SQLException {
        String sql="insert into tb_user values(null,?,md5( RIGHT(?,6)),?,?,1)";

        if(null== user.getPics() || "".equals(user.getPics())){
            user.setPics("image/1.png");
        }
        return db.update(sql,user.getUname(),user.getTel(),user.getPics());
    }
    public List<User> find(User user){
        return  null;
    }

    public List<Map<String,Object>> findMap(Map<String,Object> map)throws Exception{

        StringBuffer sb= new StringBuffer();
        sb.append("select uid,uname,upwd,tel,pics,state from tb_user where 1=1");
        List<Object> list =new ArrayList<>();
        if(null !=map.get("uid")){
            sb.append(" and uid =?");
            list.add(map.get("uid"));
        }
        if(null !=map.get("uname")){
            sb.append(" and uname =?");
            list.add(map.get("uname"));
        }
        if(null !=map.get("upwd")){
            sb.append(" and upwd =?");
            list.add(map.get("upwd"));
        }
        if(null !=map.get("tel")){
            sb.append(" and tel =?");
            list.add(map.get("tel"));
        }
        if(null !=map.get("state")){
            sb.append(" and state =?");
            list.add(map.get("state"));
        }
        sb.append(" order by state asc");
        return db.find(sb.toString(),list.toArray());
    }

    public List<User> findAll() throws Exception {
        String sql="select uid,uname,upwd,tel,pics,state from tb_user";
        return db.finds(sql,User.class);
    }

    public static void main(String[] args) throws Exception{
        UserDAO dao = new UserDAO();
        List<User> list =dao.findAll();
        for (User u: list){
            System.out.println(u);
        }
    }
}
