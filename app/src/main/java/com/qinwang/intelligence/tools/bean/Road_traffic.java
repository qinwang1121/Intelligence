package com.qinwang.intelligence.tools.bean;

import java.util.List;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/4/22
 * @Description:com.qinwang.intelligence.tools.bean
 * @Version:1.0
 * @function:
 */
public class Road_traffic {
    private String road_name;               //道路名称
    private List<congestion_sections> mList;//拥堵路段详情

    public String getRoad_name() {
        return road_name;
    }

    public void setRoad_name(String road_name) {
        this.road_name = road_name;
    }

    public List<congestion_sections> getmList() {
        return mList;
    }

    public void setmList(List<congestion_sections> mList) {
        this.mList = mList;
    }

    class congestion_sections{
        private String section_desc;    //路段拥堵语义化描述
        private int status;             //路段拥堵评价
        private double speed;           //平均通行速度
        private int congestion_distance;//拥堵距离
        private String congestion_trend;//较10分钟前拥堵趋势

        public String getSection_desc() {
            return section_desc;
        }

        public void setSection_desc(String section_desc) {
            this.section_desc = section_desc;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public double getSpeed() {
            return speed;
        }

        public void setSpeed(double speed) {
            this.speed = speed;
        }

        public int getCongestion_distance() {
            return congestion_distance;
        }

        public void setCongestion_distance(int congestion_distance) {
            this.congestion_distance = congestion_distance;
        }

        public String getCongestion_trend() {
            return congestion_trend;
        }

        public void setCongestion_trend(String congestion_trend) {
            this.congestion_trend = congestion_trend;
        }
    }
}
