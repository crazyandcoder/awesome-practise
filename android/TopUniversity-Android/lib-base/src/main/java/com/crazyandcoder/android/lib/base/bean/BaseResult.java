package com.crazyandcoder.android.lib.base.bean;

import java.io.Serializable;

/**
 * @ClassName: BaseResult
 * @Description: java类作用描述
 * @Author: crazyandcoder
 * @email: lijiwork@sina.com
 * @CreateDate: 2020/6/18 9:21 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/18 9:21 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BaseResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int code;
    private String msg;
    private T data;

    @Override
    public String toString() {
        return "BaseResult{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
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
