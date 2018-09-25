package cn.oriki.user.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

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
    private String username;

    /**
     * password
     */
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
