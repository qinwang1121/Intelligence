package com.qinwang.base;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/3/18
 * @Description:com.qinwang.base
 * @Version:1.0
 * @function:
 */
public interface MVPLisenter {

    void onSuccess(String msg);

    void onFail(String status, String msg);

}
