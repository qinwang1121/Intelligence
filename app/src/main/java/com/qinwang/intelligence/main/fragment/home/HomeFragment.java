package com.qinwang.intelligence.main.fragment.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Guideline;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.qinwang.base.BaseFragment;
import com.qinwang.intelligence.MyApplication;
import com.qinwang.intelligence.R;

public class HomeFragment extends BaseFragment {

    private static final String TAG = "HomeFragment_message";
    private static int MAP_CLICK = 1;

    private static MapView mMapView = null;
    private static Guideline Line;

    public static BaiduMap mBaiduMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        setMap();
    }

    public void initView(){
        mMapView = getView().findViewById(R.id.bMapView);
        mMapView.showZoomControls(false);   //隐藏缩放按钮

        Line = getView().findViewById(R.id.line);
    }

    public void setMap(){
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setTrafficEnabled(true);                              //显示路况
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        MapStatus mMapStatus = new MapStatus
                .Builder()
                .zoom(20)                                               //设置级别，放大地图到20倍
                .build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        mBaiduMap.setMapStatus(mMapStatusUpdate);
        mBaiduMap.setBuildingsEnabled(true);
        //开启地图的定位图层
        mBaiduMap.setMyLocationEnabled(true);
        mBaiduMap.setOnMapClickListener(listener);
    }

    /**
     * 地图单击事件监听
     */
    private static BaiduMap.OnMapClickListener listener = new BaiduMap.OnMapClickListener() {
        /**
         * 地图单击事件回调函数
         *
         * @param latLng 点击的地理坐标
         */
        @Override
        public void onMapClick(LatLng latLng) {
            MAP_CLICK ++;
            if (MAP_CLICK %2 == 0){
                Line.setGuidelinePercent(1);
            }else {
                Line.setGuidelinePercent((float)0.4);
            }
        }

        /**
         * 地图内 Poi 单击事件回调函数
         *
         * @param mapPoi    点击的 poi 信息
         */
        @Override
        public void onMapPoiClick(MapPoi mapPoi) {

        }
    };

    @Override
    public void onStart() {
        super.onStart();
        MyApplication.isFirstLoc = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        MyApplication.isFirstLoc = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        MyApplication.isFirstLoc = true;
    }

    @Override
    public void onStop() {
        super.onStop();
        MyApplication.isFirstLoc = true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MyApplication.isFirstLoc = true;
    }
}
