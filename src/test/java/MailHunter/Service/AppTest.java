package MailHunter.Service;

import MailHunter.DataAccess.model.User;
import MailHunter.Service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath*:spring-db-context.xml")
@Transactional
public class AppTest {


    UserServiceImpl impl;

    @Before
    @Rollback(true)
    public void setUp() {

        UserService impl = new UserServiceImpl();


        User user = new User("username","nikitakek","ROLE_USER");
        impl.save(user);
    }

    @Test
    public void testCount() {
        System.out.println("\n********** ACCOUNTS COUNT **********");

        Assert.assertEquals(2, impl.count());
        System.out.println("Account count: " + impl.count());
        System.out.println("********** ACCOUNTS COUNT **********");
    }

}
