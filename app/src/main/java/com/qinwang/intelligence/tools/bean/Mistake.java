package com.qinwang.intelligence.tools.bean;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/3/25
 * @Description:com.qinwang.intelligence.tools.bean
 * @Version:1.0
 * @function:
 */
public class Mistake {

    private String carBoardID;
    private String carColor;
    private String carBoardColor;
    private String carType;
    private String mistakeTime;
    private String mistakePlace;
    private String mistakeDescribe;
    private String policeName;

    public Mistake(String carBoardID, String carColor, String carBoardColor, String carType,
                   String mistakeTime, String mistakePlace, String mistakeDescribe, String policeName){
        this.carBoardID = carBoardID;
        this.carColor = carColor;
        this.carBoardColor = carBoardColor;
        this.carType = carType;
        this.mistakeTime = mistakeTime;
        this.mistakePlace = mistakePlace;
        this.mistakeDescribe = mistakeDescribe;
        this.policeName = policeName;
    }

    public String getCarBoardID() {
        return carBoardID;
    }

    public void setCarBoardID(String carBoardID) {
        this.carBoardID = carBoardID;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getCarBoardColor() {
        return carBoardColor;
    }

    public void setCarBoardColor(String carBoardColor) {
        this.carBoardColor = carBoardColor;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getMistakeTime() {
        return mistakeTime;
    }

    public void setMistakeTime(String mistakeTime) {
        this.mistakeTime = mistakeTime;
    }

    public String getMistakePlace() {
        return mistakePlace;
    }

    public void setMistakePlace(String mistakePlace) {
        this.mistakePlace = mistakePlace;
    }

    public String getMistakeDescribe() {
        return mistakeDescribe;
    }

    public void setMistakeDescribe(String mistakeDescribe) {
        this.mistakeDescribe = mistakeDescribe;
    }

    public String getPoliceName() {
        return policeName;
    }

    public void setPoliceName(String policeName) {
        this.policeName = policeName;
    }
}
