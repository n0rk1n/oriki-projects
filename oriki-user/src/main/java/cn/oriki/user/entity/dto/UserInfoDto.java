package cn.oriki.user.entity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

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
     * real_name
     */
    private String realName;

    /**
     * identity_id
     */
    private String identityId;

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
     * create_date
     */
    private Date createDate;

    /**
     * update_date
     */
    private Date updateDate;

}
