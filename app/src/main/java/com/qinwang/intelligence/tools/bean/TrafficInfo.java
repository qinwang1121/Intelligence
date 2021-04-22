package com.qinwang.intelligence.tools.bean;

import java.util.List;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/3/30
 * @Description:com.qinwang.intelligence.tools.bean
 * @Version:1.0
 * @function:
 */
public class TrafficInfo {

    private int status;                 //状态码
    private String message;             //响应信息
    private String description;         //路况语义化描述
    private evaluation mEvaluation;     //路况整体评估
    private List<Road_traffic> mList;   //路况详细信息

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public evaluation getmEvaluation() {
        return mEvaluation;
    }

    public void setmEvaluation(evaluation mEvaluation) {
        this.mEvaluation = mEvaluation;
    }

    public List<Road_traffic> getmList() {
        return mList;
    }

    public void setmList(List<Road_traffic> mList) {
        this.mList = mList;
    }

    class evaluation{
        private int status;         //路况整体评价
        private String status_desc; //路况整体评价的语义化描述

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getStatus_desc() {
            return status_desc;
        }

        public void setStatus_desc(String status_desc) {
            this.status_desc = status_desc;
        }
    }
}
