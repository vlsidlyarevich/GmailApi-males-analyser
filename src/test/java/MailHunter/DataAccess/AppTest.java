package MailHunter.DataAccess;


import MailHunter.DataAccess.Repository.UserRepository;
import MailHunter.DataAccess.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath*:spring-db-context.xml")
@Transactional
public class AppTest {

    @Autowired
    private UserRepository userRepository;

    @Before
    @Rollback(true)
    public void setUp() {

        User user = new User("username","nikitakek","ROLE_USER");
        List<User> users = new LinkedList<User>();
        users.add(new User("vladik","lkek","ROLE_PETUH"));
        users.add(user);
        userRepository.save(users);

    }

    @Test
    public void testCount() {
        System.out.println("\n********** ACCOUNTS COUNT **********");

        Assert.assertEquals(2,userRepository.count());
        System.out.println("Account count: " + userRepository.count());
        System.out.println("********** ACCOUNTS COUNT **********");
    }

    @After
    public void clean() {
        userRepository.deleteAll();
    }
}
