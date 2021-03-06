package com.example.demo;

import org.springframework.context.annotation.Bean;

import java.sql.Connection;
import java.sql.SQLException;


interface ConnectionMaker {
     Connection makeConnection() throws ClassNotFoundException, SQLException;
}
