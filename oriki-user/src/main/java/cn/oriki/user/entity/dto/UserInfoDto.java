package cn.oriki.user.entity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户信息实体 dto
 *
 * @author oriki.wang
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserInfoDto {

    /**
     * id
     */
    private Long id;

    /**
     * username
     */
    private String username;

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
