package com.yc.usersys.web.controller;

import com.yc.usersys.entity.User;
import com.yc.usersys.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
@SuppressWarnings("all")
@WebServlet("/user.action")
public class UserServlet extends HttpServlet {

    private UserService service =new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String op = req.getParameter("op");
        if("add".equals(op)){
            doAdd(req,resp);
        }else if("login".equals(op)){
            doLogin(req,resp);
        }else if("check".equals(op)){
            doCheck(req,resp);
        }else if("findTel".equals(op)){
            doFindTel(req,resp);
        }
    }

    private void doFindTel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tel =req.getParameter("tel");
        User user =new User();
        user.setTel(tel);
        User info =service.findTel(user);
        resp.setContentType("text/html; charest=utf-8");
        PrintWriter out = resp.getWriter();
        if(info == null){
            out.write("error");
        }else{
            out.write("success");
        }
    }

    private void doCheck(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object obj =session.getAttribute("user");
        PrintWriter out = resp.getWriter();
        if(obj==null){
            out.write(0);
        }else{
            out.write(1);
        }
    }

    private void doLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uname=req.getParameter("uname");
        String upwd=req.getParameter("upwd");
        Map<String,Object> map = service.login(uname,upwd);
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter out =resp.getWriter();
        if(null==map){
            out.write("error");
        }else{
            HttpSession session= req.getSession();
            session.setAttribute("user",map);
            out.write("success");
        }
    }

    private void doAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uname =req.getParameter("uname");
        String tel =req.getParameter("tel");
        User user =new User();
        user.setTel(tel);
        user.setUname(uname);
        int result =service.add(user);

        resp.setContentType("text/html; charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.write(result);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
