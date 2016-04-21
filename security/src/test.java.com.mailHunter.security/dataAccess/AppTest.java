package dataAccess;


import com.mailHunter.security.dataAccess.model.User;
import com.mailHunter.security.dataAccess.repository.UserRepository;
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
    private UserRepository userRepository;



    @Before
    @Rollback(true)
    public void setUp() {
        // USER
        userRepository.save(new User("vlad","123","ROLE_USER"));

    }

    @Test
    public void testCount() {
        System.out.println("\n********** ACCOUNTS COUNT **********");
        System.out.println("Account count: " + userRepository.count());
        System.out.println("********** ACCOUNTS COUNT **********");
    }
}
