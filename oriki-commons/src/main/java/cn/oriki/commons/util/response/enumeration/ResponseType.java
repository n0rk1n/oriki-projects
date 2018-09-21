package cn.oriki.commons.util.response.enumeration;

import lombok.Getter;

/**
 * 响应的 type
 *
 * @author oriki.wang
 */
@Getter
public enum ResponseType {

    /**
     * 成功
     */
    SUCCESS("success"),
    /**
     * 失败
     */
    FAIL("fail"),
    /**
     * 服务器异常
     */
    EXCEPTION("exception");

    private String type;

    ResponseType(String type) {
        this.type = type;
    }

}
