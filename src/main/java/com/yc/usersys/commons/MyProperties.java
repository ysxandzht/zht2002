package com.yc.usersys.commons;

import java.io.IOException;
import java.util.Properties;

/**
 * 读取配置文件且只需读取一次即可   --》单例设计模式  --》饿汉式
 *
 * @author Administrator
 */
public class MyProperties extends Properties {

	private static final long serialVersionUID = 2809456923943821836L;
	private static MyProperties instance = new MyProperties();

    private MyProperties() {
        //加载文件
        try {
            this.load(MyProperties.class.getClassLoader().getResourceAsStream("db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MyProperties getInstance() {
        return instance;
    }
}
