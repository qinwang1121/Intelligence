package com.qinwang.intelligence.tools.bean;

import java.util.List;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/3/30
 * @Description:com.qinwang.intelligence.tools.bean
 * @Version:1.0
 * @function:
 */
public class TrafficInfo<T, M> {

    private int status;                 //状态码
    private String message;             //响应信息
    private String description;         //路况语义化描述
    private T evaluation;     //路况整体评估
    private List<M> road_traffic;   //路况详细信息

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

    public T getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(T evaluation) {
        this.evaluation = evaluation;
    }

    public List<M> getRoad_traffic() {
        return road_traffic;
    }

    public void setRoad_traffic(List<M> road_traffic) {
        this.road_traffic = road_traffic;
    }
}
