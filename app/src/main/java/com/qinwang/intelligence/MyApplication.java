package com.qinwang.intelligence;

import android.app.Application;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/3/30
 * @Description:com.qinwang.intelligence
 * @Version:1.0
 * @function:
 */
public class MyApplication extends Application {
    public static boolean isFirstLogin = true;

    public static String SERVE_KEY = "Cxz3Gc2UCPRVGk9X8PR7rH9SZGfb4rNF";

    @Override
    public void onCreate() {
        super.onCreate();
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
    }
}
