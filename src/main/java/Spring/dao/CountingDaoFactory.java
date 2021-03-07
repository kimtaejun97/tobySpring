package Spring.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration
public class CountingDaoFactory {
    @Bean
    public UserDao makeUserDao(){
        UserDao userDao = new UserDao();
        userDao.setDataSource(dataSource());
        return userDao;

    }
    @Bean
    public ConnectionMaker connectionMaker(){
        return new CountingConnectionMaker(dataSource());
    }

    @Bean
    public DataSource dataSource()
    {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setUrl("jdbc:mysql://localhost/tobyspring");
        dataSource.setUsername("root");
        dataSource.setPassword("axxz1314");
        return dataSource;
    }
}
