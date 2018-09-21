package cn.oriki.user.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户信息实体
 *
 * @author oriki.wang
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserInfo {

    /**
     * id
     */
    private Long id;

    /**
     * username
     */
    private String username;

    /**
     * password
     */
    @JSONField(serialize = false)
    private String password;

    /**
     * phone_number
     */
    private String phoneNumber;

    /**
     * nick_name
     */
    private String nickName;

    /**
     * age
     */
    private Integer age;

    /**
     * salary
     */
    private Double salary;

}
