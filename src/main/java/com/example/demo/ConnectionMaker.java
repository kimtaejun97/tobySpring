package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

interface ConnectionMaker {
    public Connection makeConnection() throws ClassNotFoundException, SQLException;
}
