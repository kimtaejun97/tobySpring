package Spring.dao;

import java.sql.Connection;
import java.sql.SQLException;


interface ConnectionMaker {
     Connection makeConnection() throws ClassNotFoundException, SQLException;
}
