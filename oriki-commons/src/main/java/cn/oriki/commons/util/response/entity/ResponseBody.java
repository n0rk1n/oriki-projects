package cn.oriki.commons.util.response.entity;

import lombok.*;

/**
 * Http 请求信息返回实体
 *
 * @author oriki.wang
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ResponseBody {

    /**
     * 消息实体
     */
    private Object message;
    /**
     * 返回 code，枚举 ResponseCode
     */
    private String code;
    /**
     * 种类 ，枚举 ResponseType
     */
    private String type;

}
