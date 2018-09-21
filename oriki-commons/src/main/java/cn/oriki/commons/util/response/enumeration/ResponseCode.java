package cn.oriki.commons.util.response.enumeration;

import lombok.Getter;

/**
 * http 返回实体 code
 *
 * @author oriki.wang
 */
@Getter
public enum ResponseCode {

    /**
     * 成功
     */
    SUCCESS("200"),
    /**
     * 服务器异常
     */
    EXCEPTION("500");

    private String code;

    ResponseCode(String code) {
        this.code = code;
    }

}
