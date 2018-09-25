package cn.oriki.user.dao.repository;

import cn.oriki.user.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserInfo Repository
 *
 * @author oriki.wang
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
}
