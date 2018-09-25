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
@Transactional
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
            userInfo.setPassword(Md5s.getMd5(oldPassword));

            // set create_date & update_date
            userInfo.setCreateDate(new Date());
            userInfo.setUpdateDate(new Date());

            // set del_tag false
            userInfo.setDelTag(false);

            return this.userInfoRepository.save(userInfo);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("get password's md5 fail , " + e);
            userInfo.setId(null);
            return userInfo;
        }
    }

    public UserInfo queryByUsernameAndPassword(String username, String password) {
        try {
            password = Md5s.getMd5(password);
            return this.userInfoRepository.queryByUsernameAndPassword(username, password);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("get password's md5 fail" + e);
            return null;
        }
    }

    public boolean existsByUserName(String username) {
        // 根据用户名判断用户是否存在
        return this.userInfoRepository.existsByUsername(username);
    }

}
