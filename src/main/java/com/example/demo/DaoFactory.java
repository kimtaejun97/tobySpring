package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {
    @Bean
    public UserDao makeUserDao() {

        return new UserDao(connectionMaker());
    }
    @Bean
    public MessageDao makeMessageDao(){
        return new MessageDao(connectionMaker());
    }

    @Bean
    public AccountDao makeAccountDao(){
        return new AccountDao(connectionMaker());
    }
    @Bean
    public ConnectionMaker connectionMaker(){
        return new NConnectionMaker();
    }
}
