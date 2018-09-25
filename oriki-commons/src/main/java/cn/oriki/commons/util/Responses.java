package cn.oriki.commons.util;

import cn.oriki.commons.util.response.entity.ResponseBody;
import cn.oriki.commons.util.response.enumeration.ResponseCode;
import cn.oriki.commons.util.response.enumeration.ResponseType;

import java.util.Objects;

/**
 * 生成 http 请求返回实体的字符串格式工具
 *
 * @author oriki.wang
 */
public class Responses {

    /**
     * 返回成功的 Response 实体
     *
     * @param object message 信息
     * @return Response 实体的 json 格式
     */
    public static String returnSuccess(Object object) {
        ResponseBody responseBody = newResponseBody(ResponseCode.SUCCESS, ResponseType.SUCCESS, object);
        return Jsons.toJson(responseBody);
    }

    /**
     * 返回成功的 Response 实体
     *
     * @return Response 实体的 json 格式
     */
    public static String returnSuccess() {
        return returnSuccess(new Object());
    }

    /**
     * 返回失败的 Response 实体
     *
     * @return
     */
    public static String returnFail() {
        ResponseBody responseBody = newResponseBody(ResponseCode.EXCEPTION, ResponseType.FAIL, new Object());
        return Jsons.toJson(responseBody);
    }

    /**
     * 返回失败的 Response 实体
     *
     * @return
     */
    public static String returnFail(String message) {
        ResponseBody responseBody = newResponseBody(ResponseCode.EXCEPTION, ResponseType.FAIL, message);
        return Jsons.toJson(responseBody);
    }

    /**
     * 返回服务器异常的 Response 实体
     *
     * @param e 异常信息
     * @return Response 实体的 json 格式
     */
    public static String returnException(Exception e) {
        ResponseBody responseBody = newResponseBody(ResponseCode.EXCEPTION, ResponseType.EXCEPTION, e.getMessage());
        return Jsons.toJson(responseBody);
    }

    private static ResponseBody newResponseBody(ResponseCode code, ResponseType type, Object object) {
        ResponseBody responseBody = new ResponseBody();
        {
            responseBody.setCode(code.getCode());
            responseBody.setType(type.getType());
            if (Objects.nonNull(object)) {
                responseBody.setMessage(object);
            } else {
                responseBody.setMessage(new Object());
            }
        }
        return responseBody;
    }

}
