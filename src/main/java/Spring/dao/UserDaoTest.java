package Spring.dao;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;



public class UserDaoTest {
    @Test
    public void addAndGet() throws SQLException, ClassNotFoundException {
//        ApplicationContext context =new GenericXmlApplicationContext("applicationContext.xml");
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao userDao = context.getBean("makeUserDao", UserDao.class);

        User user1 = new User("kim", "김", "no1");
        User user2 = new User("park", "박", "no2");
        User user3 = new User("Lee", "이", "no3");

        userDao.deleteAll();
        assertThat(userDao.getCount(), is(0));

        userDao.add(user1);
        userDao.add(user2);
        userDao.add(user3);
        assertThat(userDao.getCount(), is(3));

        User userget1 = userDao.get(user1.getId());
        assertThat(userget1.getName(), is(user1.getName()));
        assertThat(userget1.getPassword(), is(user1.getPassword()));

        User userget2 = userDao.get(user2.getId());
        assertThat(userget2.getName(), is(user2.getName()));
        assertThat(userget2.getPassword(), is(user2.getPassword()));

        User userget3 = userDao.get(user3.getId());
        assertThat(userget3.getName(), is(user3.getName()));
        assertThat(userget3.getPassword(), is(user3.getPassword()));


        userDao.deleteAll();
        assertThat(userDao.getCount(), is(0));

    }

    @Test
    public void count() throws SQLException, ClassNotFoundException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);

        UserDao userDao = context.getBean("makeUserDao", UserDao.class);
        User user1 = new User("kim", "김", "no1");
        User user2 = new User("park", "박", "no2");
        User user3 = new User("Lee", "이", "no3");
        int count = 0;

        userDao.deleteAll();
        count = userDao.getCount();

        userDao.add(user1);
        assertThat(userDao.getCount(), is(count +1));
        count = userDao.getCount();

        userDao.add(user2);
        assertThat(userDao.getCount(), is(count +1));
        count = userDao.getCount();

        userDao.add(user3);
        assertThat(userDao.getCount(), is(count +1));
        count = userDao.getCount();

        userDao.deleteAll();
        assertThat(userDao.getCount(), is(0));

    }

    @Test(expected= EmptyResultDataAccessException.class)
    public void getUserFailure() throws SQLException, ClassNotFoundException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao userDao = context.getBean("makeUserDao", UserDao.class);

        userDao.deleteAll();
        assertThat(userDao.getCount(), is(0));

        userDao.get("unknown_id");
    }
}
