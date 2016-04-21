package service;

import com.mailHunter.security.dataAccess.model.User;
import com.mailHunter.security.service.UserService;
import com.mailHunter.security.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath*:spring-db-context.xml")
@Transactional
public class AppTest {


    @Autowired
    private UserService userService;

    @Before
    @Rollback(true)
    public void setUp() {
        // USER
        userService = new UserServiceImpl();
        userService.save(new User("vlad","123","ROLE_USER"));

    }

    @Test
    public void testCount() {
        System.out.println("\n********** ACCOUNTS COUNT **********");
        System.out.println("Account count: " + userService.count());
        System.out.println("********** ACCOUNTS COUNT **********");
    }
}
