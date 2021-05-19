package com.qinwang.intelligence.tools.bean;

import java.util.List;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/4/22
 * @Description:com.qinwang.intelligence.tools.bean
 * @Version:1.0
 * @function:
 */
public class road_traffic<M> {
    private String road_name;               //道路名称
    private List<M> congestion_sections;//拥堵路段详情

    public String getRoad_name() {
        return road_name;
    }

    public void setRoad_name(String road_name) {
        this.road_name = road_name;
    }

    public List<M> getCongestion_sections() {
        return congestion_sections;
    }

    public void setCongestion_sections(List<M> congestion_sections) {
        this.congestion_sections = congestion_sections;
    }
}
