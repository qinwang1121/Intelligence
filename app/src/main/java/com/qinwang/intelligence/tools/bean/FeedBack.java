package com.qinwang.intelligence.tools.bean;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/3/21
 * @Description:com.qinwang.intelligence.tools.bean
 * @Version:1.0
 * @function:
 */
public class FeedBack {

    private String Email;
    private String message;

    public FeedBack(){

    }

    public FeedBack(String Email, String message){
        this.Email = Email;
        this.message = message;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
