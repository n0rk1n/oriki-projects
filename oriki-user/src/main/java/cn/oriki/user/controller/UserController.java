package cn.oriki.user.controller;

import cn.oriki.commons.util.Responses;
import cn.oriki.user.entity.UserInfo;
import cn.oriki.user.entity.dto.UserInfoDto;
import cn.oriki.user.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * User Controller
 *
 * @author oriki.wang
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserInfoService userInfoService;

    @Autowired
    public UserController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping(value = "/login")
    public String login(@RequestParam(name = "username") String username,
                        @RequestParam(name = "password") String password) {
        LOGGER.info("当前执行登录用户操作：" + username + "");

        // check
        assert username != null;
        assert password != null;

        // success
        UserInfoDto userInfoDto = new UserInfoDto();
        {
            userInfoDto.setId(-1L);
            userInfoDto.setUsername("default-user");
            userInfoDto.setPhoneNumber("18888888888");
            userInfoDto.setNickName("default-nick-name");
            userInfoDto.setAge(-1);
            userInfoDto.setSalary(-1.0D);
        }

        return Responses.returnSuccess(userInfoDto);
    }

    @PostMapping(value = "/register")
    public String register(@RequestParam(name = "username") String username,
                           @RequestParam(name = "password") String password,
                           @RequestParam(name = "phoneNumber", required = false) String phoneNumber,
                           @RequestParam(name = "nickName", required = false) String nickName,
                           @RequestParam(name = "age", required = false) Integer age,
                           @RequestParam(name = "salary", required = false) Double salary) {
        UserInfo userInfo = new UserInfo();
        {
            userInfo.setUsername(username);
            userInfo.setPassword(password);
            userInfo.setPhoneNumber(phoneNumber);
            userInfo.setNickName(nickName);
            userInfo.setAge(age);
            userInfo.setSalary(salary);
        }
        
        UserInfo uInfo = this.userInfoService.save(userInfo);

        if (Objects.nonNull(uInfo.getId())) {
            return Responses.returnSuccess();
        }
        return Responses.returnFail();
    }

}
