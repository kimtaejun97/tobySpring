package Spring.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.support.DaoSupport;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;



public class UserDaoTest {
    private UserDao userDao;
    private User user1;
    private User user2;
    private User user3;

    @Before
    public void SetUp() throws SQLException, ClassNotFoundException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        this.userDao = context.getBean("makeUserDao", UserDao.class);

        user1 = new User("kim", "김", "no1");
        user2 = new User("park", "박", "no2");
        user3 = new User("Lee", "이", "no3");

        userDao.deleteAll();
        assertThat(userDao.getCount(), is(0));

    }

    @After
    public void clearData() throws SQLException, ClassNotFoundException {
        userDao.deleteAll();
        assertThat(userDao.getCount(), is(0));
    }


    @Test
    public void addAndGet() throws SQLException, ClassNotFoundException {
//        ApplicationContext context =new GenericXmlApplicationContext("applicationContext.xml");

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

    }

    @Test
    public void count() throws SQLException, ClassNotFoundException {
        int count = userDao.getCount();

        userDao.add(user1);
        assertThat(userDao.getCount(), is(count +1));
        count = userDao.getCount();

        userDao.add(user2);
        assertThat(userDao.getCount(), is(count +1));
        count = userDao.getCount();

        userDao.add(user3);
        assertThat(userDao.getCount(), is(count +1));
        count = userDao.getCount();

    }

    @Test(expected= EmptyResultDataAccessException.class)
    public void getUserFailure() throws SQLException, ClassNotFoundException {

        userDao.get("unknown_id");
    }
}
