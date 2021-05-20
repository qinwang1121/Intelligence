package com.qinwang.intelligence.tools.net;

import com.qinwang.intelligence.MyApplication;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/3/21
 * @Description:com.qinwang.intelligence.tools.net
 * @Version:1.0
 * @function:
 */
public class BaseAPI {

    public final static String USER_WEB_ROAD_SERVE = "http://api.map.baidu.com/traffic/v1/road?road_name=东二环&city=北京市&ak=你的AK";

    //寝室测试接口
    public final static String Base_URL = "http://169.254.65.42/transport/";
//    public final static String Base_URL = "http://192.168.43.232/transport/";

    public final static String URL_LOGIN = Base_URL + "login";

    public final static String URL_REGISTER = Base_URL + "register";

    public final static String URL_FEEDBACK = Base_URL + "feedback";

    public final static String URL_UPLOADING = Base_URL + "insert";

    public final static String URL_SEARCH = Base_URL + "message";

    /**
     * 市民端路况服务接口
     * @param road_name 道路名称
     * @param city 所在城市
     * @return
     */
    public final static String USER_WEB_ROAD_SERVE(String road_name, String city){
        return "http://api.map.baidu.com/traffic/v1/road?road_name= " + road_name +
                "&city=" + city +
                "&ak=" + MyApplication.SERVE_KEY;
    }

    /**
     * 交警端路况接口
     * @param center 中心点坐标
     * @param radius 查询半径 单位：米，取值范围[1,1000]
     * @return
     */
    public final static String ADMIN_WEB_RIM_SERVE(String center, int radius){
        return "http://api.map.baidu.com/traffic/v1/around?ak=" + MyApplication.SERVE_KEY +
                "&center=" + center +
                "&radius=" + radius +
                "&coord_type_input=gcj02&coord_type_output=gcj02";
    }

    /**
     * 交警端路况接口
     * @param center 中心点坐标
     * @param radius 查询半径 单位：米，取值范围[1,1000]
     * @param road_grade 道路等级  默认值：road_grade=0 道路等级对应表如下： 0：全部驾车道路 1：高速路 2：环路及快速路 3：主干路 4：次干路 5：支干路
     * @return
     */
    public final static String ADMIN_WEB_RIM_SERVE(String center, String radius, int road_grade){
        return "http://api.map.baidu.com/traffic/v1/around?ak=" + MyApplication.SERVE_KEY +
                "&center=" + center +
                "&road_grade=" + road_grade +
                "&radius=" + radius +
                "&coord_type_input=gcj02&coord_type_output=gcj02";
    }
}
