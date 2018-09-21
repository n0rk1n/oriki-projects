package cn.oriki.commons.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class ResponsesTest {

    @Test
    public void returnSuccess() {
        String s = Responses.returnSuccess(new Object());
        assertEquals("{\"code\":\"200\",\"message\":{},\"type\":\"success\"}", s);
        // {"code":"200","message":{},"type":"success"}

        String s1 = Responses.returnSuccess();
        assertEquals("{\"code\":\"200\",\"message\":{},\"type\":\"success\"}", s1);
        // {"code":"200","message":{},"type":"success"}
    }

    @Test
    public void returnException() {
        String s = Responses.returnException(new RuntimeException("test"));
        assertEquals("{\"code\":\"500\",\"message\":\"test\",\"type\":\"exception\"}", s);
        // {"code":"500","message":"test","type":"exception"}
    }

}