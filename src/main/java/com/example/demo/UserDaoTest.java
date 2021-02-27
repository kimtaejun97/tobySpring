package com.example.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class UserDaoTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ApplicationContext context =new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao userDao = context.getBean("makeUserDao", UserDao.class);

        User user = new User();
        user.setId("1");
        user.setName("kimtaejun");
        user.setPassword("1111");


        userDao.add(user);
        System.out.println(user.getId()+" 등록 성공");

        User user2;
        user2 =userDao.get("1");

        System.out.println(user2.getId() + " 조회 성공");
        System.out.println(user2.getName());

        userDao.afterTest();

    }
}
