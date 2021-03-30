package com.qinwang.intelligence.tools.bean;

import java.util.List;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/3/28
 * @Description:com.qinwang.intelligence.tools.bean
 * @Version:1.0
 * @function:
 */
public class CarMsg<T> {

    private String carUserName;
    private String carUserId;
    private String carUserGender;
    private String carName;
    private String carType;
    private String carColor;
    private String carBoardID;
    private String carBoardColor;
    public List<T> list;

    public CarMsg(String carUserName, String carUserId, String carUserGender,
                  String carName, String carType, String carColor, String carBoardColor,
                  List<T> list){
        this.carUserName = carUserName;
        this.carUserId = carUserId;
        this.carUserGender = carUserGender;
        this.carName = carName;
        this.carType = carType;
        this.carColor = carColor;
        this.carBoardColor = carBoardColor;
        this.list = list;
    }

    public String getCarUserName() {
        return carUserName;
    }

    public void setCarUserName(String carUserName) {
        this.carUserName = carUserName;
    }

    public String getCarUserId() {
        return carUserId;
    }

    public void setCarUserId(String carUserId) {
        this.carUserId = carUserId;
    }

    public String getCarUserGender() {
        return carUserGender;
    }

    public void setCarUserGender(String carUserGender) {
        this.carUserGender = carUserGender;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getCarBoardID() {
        return carBoardID;
    }

    public void setCarBoardID(String carBoardID) {
        this.carBoardID = carBoardID;
    }

    public String getCarBoardColor() {
        return carBoardColor;
    }

    public void setCarBoardColor(String carBoardColor) {
        this.carBoardColor = carBoardColor;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}

