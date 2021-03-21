package com.qinwang.intelligence.tools.bean;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/3/14
 * @Description:com.qinwang.traffic.tools.bean
 * @Version:1.0
 * @function:
 */
public class Response<T> {

    private int status;

    private String msg;

    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public boolean isSuccess(){
        if (getStatus() == 200){
            return true;
        }else return false;
    }

}
