package com.crazyandcoder.university.model;

public class Response<T> extends NatureObject {
    private static final long serialVersionUID = -5609187686109612101L;
    /**
     * 请求响应编码
     */
    private Integer code;
    /**
     * 请求响应消息
     */
    private String msg;
    /**
     * 请求响应实体数据
     */
    private T data;

    public static <T> Response<T> successResult() {
        return new Response<T>(ResponseCode.SUCCESS, "成功");
    }

    public static <T> Response<T> successResult(String message) {
        return new Response<T>(ResponseCode.SUCCESS, message);

    }

    public static <T> Response<T> successResult(String message, T data) {
        return new Response<T>(ResponseCode.SUCCESS, message).setData(data);
    }


    public static <T> Response<T> failResult() {
        return new Response<T>(ResponseCode.FAIL, "失败");
    }

    public static <T> Response<T> failResult(String message) {
        return new Response<T>(ResponseCode.FAIL, message);
    }

    public static <T> Response<T> failResult(Integer code, String message) {
        return new Response<T>(code, message);
    }

    public static <T> Response<T> failResult(String message, T data) {
        return new Response<T>(ResponseCode.FAIL, message).setData(data);
    }


    public Response(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public Response<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public Response<T> setData(T data) {
        this.data = data;
        return this;
    }

}
