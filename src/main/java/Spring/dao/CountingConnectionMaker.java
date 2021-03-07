package Spring.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class CountingConnectionMaker implements ConnectionMaker{
    int count =0;
    DataSource dataSource;

    public CountingConnectionMaker(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        this.count ++;
        return dataSource.getConnection();
    }
    public int getCount(){
        return this.count;
    }
}
