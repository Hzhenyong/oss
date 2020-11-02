package com.ruoyi.ai.face.rpc;

/**
 * @author hanzhenyong
 * @create 2020-11-02 20:34
 */
public class Response<T> {

    private int code = -1;
    private String msg = "success";
    private T data;

    public static <T> Response<T> newSuccessResponse(T data) {
        return newResponse(data, 0, "success");
    }

    public static <T> Response<T> newFailedResponse(Integer code, String message) {
        return newResponse(null, code, message);
    }

    public static <T> Response<T> newFailedResponse(ErrorCode ErrorCode) {
        return newResponse(null, ErrorCode.getCode(), ErrorCode.getDesc());
    }

    public static <T> Response<T> newFailedResponse(ErrorCode ErrorCode, String message) {
        return newResponse(null, ErrorCode.getCode(), message);
    }

    public static <T> Response<T> newResponse(T data, Integer code, String message) {
        Response<T> response = new Response<T>();
        response.setCode(code);
        response.setMsg(message);
        if (data != null && data instanceof String && "".equals(data)) {
            response.setData(null);
        } else {
            response.setData(data);
        }
        return response;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
