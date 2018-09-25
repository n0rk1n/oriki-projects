package cn.oriki.user.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户信息实体
 *
 * @author oriki.wang
 */
@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity
@Table(name = "t_user_info")
public class UserInfo {

    /**
     * id
     */
    @Id
    @Column(name = "id", unique = true, updatable = false, length = 32)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * username
     */
    @Column(name = "user_name", unique = true, updatable = false, length = 16)
    private String username;

    /**
     * password
     */
    @Column(name = "password", length = 32)
    private String password;

    /**
     * nick_name
     */
    @Column(name = "nick_name", length = 32)
    private String nickName;

    /**
     * real_name
     */
    @Column(name = "real_name", length = 64)
    private String realName;

    /**
     * phone_number
     */
    @Column(name = "phone_number", length = 11)
    private String phoneNumber;

    /**
     * age
     */
    @Column(name = "age")
    private Integer age;

    /**
     * 身份证号码
     */
    @Column(name = "identity_id", length = 18)
    private String identityId;

    /**
     * create_date
     */
    @Column(name = "crate_date")
    private Date createDate;

    /**
     * update_date
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * del_tag true 删除 false 正常
     */
    @Column(name = "del_tag")
    private Boolean delTag;

}
