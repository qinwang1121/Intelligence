package com.qinwang.intelligence.tools.bean;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/1/28
 * @Description:com.ningya.intelligent_transportation_equipment.tools.bean
 * @Version:1.0
 * @function:
 */
public class User {

    private String userName;

    private String passWord;

    private String gender;

    private String id;

    private String phone;

    private String home;

    private String power;

    public User(String userName, String passWord, String gender,
                String ID, String phone, String home, String power){
        this.userName = userName;
        this.passWord = passWord;
        this.gender = gender;
        this.id = ID;
        this.phone = phone;
        this.home = home;
        this.power = power;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

}
