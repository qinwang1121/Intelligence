package com.qinwang.intelligence.tools.bean;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/5/10
 * @Description:com.qinwang.intelligence.tools.bean
 * @Version:1.0
 * @function:
 */
public class evaluation {

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
