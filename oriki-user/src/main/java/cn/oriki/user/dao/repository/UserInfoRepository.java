package cn.oriki.user.dao.repository;

import cn.oriki.user.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserInfo Repository
 *
 * @author oriki.wang
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    /**
     * 根据用户名称和密码查询用户信息
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     */
    UserInfo queryByUsernameAndPassword(String username, String password);

    /**
     * 根据用户名称判断用户是否存在
     *
     * @param username 用户名
     * @return 用户信息
     */
    boolean existsByUsername(String username);

}
