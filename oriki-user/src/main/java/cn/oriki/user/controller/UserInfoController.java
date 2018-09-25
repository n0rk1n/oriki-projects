package cn.oriki.user.controller;

import cn.oriki.commons.util.Responses;
import cn.oriki.commons.util.Strings;
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
public class UserInfoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoController.class);

    private final UserInfoService userInfoService;

    @Autowired
    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    /**
     * UserInfo Register
     *
     * @param username    用户名
     * @param password    密码
     * @param nickName    昵称
     * @param realName    真实姓名
     * @param phoneNumber 电话号码
     * @param age         年两
     * @param identityId  身份证号
     * @return 是否成功注册的 Response 实体
     */
    @PostMapping(value = "/register")
    public String register(@RequestParam(name = "username") String username,
                           @RequestParam(name = "password") String password,
                           @RequestParam(name = "nickName", required = false) String nickName,
                           @RequestParam(name = "realName", required = false) String realName,
                           @RequestParam(name = "phoneNumber", required = false) String phoneNumber,
                           @RequestParam(name = "age", required = false) Integer age,
                           @RequestParam(name = "identityId", required = false) String identityId) {
        {
            if (!checkUsername(username)) {
                return Responses.returnFail("用户名称不合法");
            }
            if (!checkPassword(password)) {
                return Responses.returnFail("密码不合法");
            }
        }

        UserInfo userInfo = new UserInfo();
        {
            userInfo.setUsername(username);
            userInfo.setPassword(password);
            userInfo.setNickName(nickName);
            userInfo.setRealName(realName);
            userInfo.setPhoneNumber(phoneNumber);
            userInfo.setAge(age);
            userInfo.setIdentityId(identityId);
        }

        LOGGER.info("用户名：" + username + " 正在执行注册操作。");
        UserInfo uInfo = this.userInfoService.save(userInfo);

        if (uInfo.getId() != null) {
            return Responses.returnSuccess();
        }
        return Responses.returnFail();
    }

    @GetMapping(value = "/login")
    public String login(@RequestParam(name = "username") String username,
                        @RequestParam(name = "password") String password) {
        {
            if (!checkUsername(username)) {
                return Responses.returnFail("用户名称不合法");
            }
            if (!checkPassword(password)) {
                return Responses.returnFail("密码不合法");
            }
        }

        UserInfo userInfo = this.userInfoService.queryByUsernameAndPassword(username, password);

        if (Objects.nonNull(userInfo)) {
            if (!userInfo.getDelTag()) {
                UserInfoDto userInfoDto = new UserInfoDto();
                {
                    userInfoDto.setId(userInfo.getId());
                    userInfoDto.setUsername(userInfo.getUsername());
                    userInfoDto.setRealName(userInfo.getRealName());
                    userInfoDto.setIdentityId(userInfo.getIdentityId());
                    userInfoDto.setPhoneNumber(userInfo.getPhoneNumber());
                    userInfoDto.setNickName(userInfo.getNickName());
                    userInfoDto.setAge(userInfo.getAge());
                    userInfoDto.setCreateDate(userInfo.getCreateDate());
                    userInfoDto.setUpdateDate(userInfo.getUpdateDate());
                }

                return Responses.returnSuccess(userInfoDto);
            } else {
                return Responses.returnFail("登录用户已被注销");
            }
        }
        return Responses.returnFail();
    }

    /**
     * 不为空 ， 且长度在 6-18 之间
     *
     * @param username 用户名
     * @return 合法，返回 true
     */
    private boolean checkUsername(String username) {
        if (Strings.isBlank(username)) {
            return false;
        }

        int length = username.length();
        return length >= 6 && length <= 18;
    }

    /**
     * 不为空 ， 且长度在 6-18 之间
     *
     * @param password 用户名
     * @return 合法，返回 true
     */
    private boolean checkPassword(String password) {
        if (Strings.isBlank(password)) {
            return false;
        }

        int length = password.length();
        return length >= 6 && length <= 18;
    }

}
