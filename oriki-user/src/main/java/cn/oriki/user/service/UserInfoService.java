package cn.oriki.user.service;

import cn.oriki.commons.util.Md5s;
import cn.oriki.user.dao.repository.UserInfoRepository;
import cn.oriki.user.entity.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * UserInfo Service
 *
 * @author oriki.wang
 */
@Service
@Transactional(rollbackOn = {Exception.class})
public class UserInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoService.class);

    private final UserInfoRepository userInfoRepository;

    @Autowired
    public UserInfoService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    /**
     * UserInfo 添加方法
     * <p>
     * 会对明文密码进行加密
     * <p>
     * 成功会获取数据库插入 id ，失败 id 为 null
     *
     * @param userInfo UserInfo
     * @return UserInfo 如果成功携带 id，如果失败 id 为 null
     */
    public UserInfo save(UserInfo userInfo) {
        try {
            // set Id null
            userInfo.setId(null);

            // md5 password
            String oldPassword = userInfo.getPassword();
            userInfo.setPassword(this.encryption(oldPassword));

            // set create_date & update_date
            userInfo.setCreateDate(new Date());
            userInfo.setUpdateDate(new Date());

            // set del_tag false
            userInfo.setDelTag(false);

            return this.userInfoRepository.save(userInfo);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("get password's md5 fail , " + e);
            // controller 校验成功失败的依据
            userInfo.setId(null);
            return userInfo;
        }
    }

    /**
     * 根据用户名和密码登录的方法
     *
     * @param username 用户名
     * @param password 密码（未加密
     * @return 如果存在用户，返回 UserInfo 信息，即使被注销用户也会返回
     */
    public UserInfo queryByUsernameAndPassword(String username, String password) {
        try {
            password = this.encryption(password);
            return this.userInfoRepository.queryByUsernameAndPassword(username, password);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("get password's md5 fail" + e);
            return null;
        }
    }

    /**
     * 根据用户名判断用户是否存在
     *
     * @param username 用户名
     * @return 存在 返回 true
     */
    public boolean existsByUserName(String username) {
        return this.userInfoRepository.existsByUsername(username);
    }

    /**
     * 加密方法，不可逆
     *
     * @param oldPassword 未加密
     * @return 加密后的代码
     * @throws NoSuchAlgorithmException 未匹配加密方式抛出
     */
    private String encryption(String oldPassword) throws NoSuchAlgorithmException {
        return Md5s.getMd5(oldPassword);
    }

}
