package cn.oriki.user.test;

import cn.oriki.user.controller.UserInfoController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserInfoTest {

    @Autowired
    private UserInfoController userInfoController;

    @Test
    public void test(){

    }

}
