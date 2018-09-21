package cn.oriki.user.controller;

import cn.oriki.commons.util.Responses;
import cn.oriki.user.entity.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * User Controller
 *
 * @author oriki.wang
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @GetMapping(value = "/login")
    public String login(@RequestParam(name = "username") String username,
                        @RequestParam(name = "password") String password) {
        LOGGER.info("当前执行登录用户操作：" + username + "");

        // check
        assert username != null;
        assert password != null;

        // success
        UserInfo userInfo = new UserInfo();
        {
            userInfo.setId(-1L);
            userInfo.setUsername("default-user");
            userInfo.setPassword("******");
            userInfo.setPhoneNumber("18888888888");
            userInfo.setNickName("default-nick-name");
            userInfo.setAge(-1);
            userInfo.setSalary(0.0D);
        }

        return Responses.returnSuccess(userInfo);
    }

}
