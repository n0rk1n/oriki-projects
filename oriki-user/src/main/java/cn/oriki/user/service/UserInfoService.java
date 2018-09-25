package cn.oriki.user.service;

import cn.oriki.commons.util.Md5s;
import cn.oriki.user.dao.repository.UserInfoRepository;
import cn.oriki.user.entity.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

/**
 * UserInfo Service
 *
 * @author oriki.wang
 */
@Service
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
     * @param userInfo
     * @return
     */
    public UserInfo save(UserInfo userInfo) {
        try {
            String password = userInfo.getPassword();
            userInfo.setPassword(Md5s.getMd5(password));
            return this.userInfoRepository.save(userInfo);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("get password's md5 fail , " + e);
            userInfo.setId(null);
            return userInfo;
        }
    }

}
