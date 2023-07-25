package com.yc.usersys.web.controller;

import com.yc.usersys.entity.User;
import com.yc.usersys.service.UserService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

@WebServlet("/fileupload.action")
public class UserFileUploadServlet extends HttpServlet {
    private final long MAXSIZE = 1024 * 10 * 10;//设置文件大小
    //设置文件存储路径
    private final String IMAGE_PATH = "/user_pics/";
    private UserService service =new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //如果获取服务器的路径
        System.out.println(req.getServletContext().getRealPath("/"));
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        System.out.println(isMultipart+"----------------");
        DiskFileItemFactory factory =new DiskFileItemFactory();
        ServletFileUpload upload =new ServletFileUpload(factory);
        upload.setFileSizeMax(MAXSIZE);
        User user =new User();
        try{
           List<FileItem> items =upload.parseRequest(req);
           for(FileItem item: items) {
               if (item.isFormField()) {
                   //普通表单元素
                   String name = item.getFieldName();
                   String value= item.getString("UTF-8");
                   if ("username".equals(name)) {
                       user.setUname(value);
                   } else if ("tel".equals(name)) {
                       user.setTel(value);
                   }
               } else {
                   //文件
                   System.out.println("文件名:" + item.getName());
                   System.out.println("文件大小:" + item.getSize());
                   System.out.println("文件类型:" + item.getContentType());
                   //获取当前项目的服务器路径
                   String fileName = System.nanoTime() + "" + new Random().nextInt(1000) + item.getName();
                   File file = new File(path + ".." + IMAGE_PATH + fileName);

                   item.write(file);//将上传的文件写入到服务器中
                   user.setPics(IMAGE_PATH + fileName);
               }
           }
           System.out.println(user);
           }catch(Exception e){
            e.printStackTrace();
        }
        int result = service.add(user);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter out = resp.getWriter();
        if(result>0){
            out.println("success");
        }else{
            out.println("error");
        }
    }
}
