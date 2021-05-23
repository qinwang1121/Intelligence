package com.qinwang.intelligence.main.fragment.home;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Guideline;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qinwang.base.BaseFragment;
import com.qinwang.intelligence.MyApplication;
import com.qinwang.intelligence.R;
import com.qinwang.intelligence.main.MainActivity;
import com.qinwang.intelligence.tools.BannerListAdapter;
import com.qinwang.intelligence.tools.bean.congestion_sections;
import com.qinwang.intelligence.tools.bean.evaluation;
import com.qinwang.intelligence.tools.bean.road_traffic;
import com.qinwang.intelligence.tools.bean.TrafficInfo;
import com.qinwang.intelligence.tools.net.BaseAPI;
import com.qinwang.intelligence.tools.net.Utils;
import com.qinwang.intelligence.tools.widget.TrafficAdapter;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment {

    private static final String TAG = "HomeFragment_message";
    private static int MAP_CLICK = 1;

    private static LinearLayout linearLayout;
    private static TextView textView_message;
    private static MapView mMapView = null;
    private static Guideline Line;
    private static ListView mListView, msgList;

    public static BaiduMap mBaiduMap;

    private static final int RADIUS = 1000;

    private static Activity activity ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = getActivity();
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

        linearLayout = getView().findViewById(R.id.linearLayout);
        linearLayout.setVisibility(View.GONE);
        textView_message = getView().findViewById(R.id.textview_message);
        Line = getView().findViewById(R.id.line);
        mListView = getView().findViewById(R.id.listInfo);
        msgList = getView().findViewById(R.id.banner_list);

    }

    public void setMap(){
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setTrafficEnabled(true);                              //显示路况
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        MapStatus mMapStatus = new MapStatus
                .Builder()
                .zoom(18)                                               //设置级别，放大地图到18倍
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
                getMsg(activity,
                        MainActivity.ADMIN_LATITUDE + "," + MainActivity.ADMIN_LONGITUDE);
//                getMsg(activity, "38.894193,121.54748");
//                getMsg(activity, "39.912078,116.464303"); //北京
            }else {
                Line.setGuidelinePercent(1);
                linearLayout.setVisibility(View.GONE);
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

    public static void getMsg(final Activity activity, final String mLatLng){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                    Log.d("HOME1", new String(Utils.getUtil(Utils.getSet(
//                            "http://api.map.baidu.com/traffic/v1/around?ak=Cxz3Gc2UCPRVGk9X8PR7rH9SZGfb4rNF" +
//                                    "&center=39.912078,116.464303&radius=1000&coord_type_input=gcj02&coord_type_output=gcj02"))));
                    final TrafficInfo trafficInfo = new Gson().fromJson(new String(
                            Utils.getUtil(Utils.getSet(BaseAPI.ADMIN_WEB_RIM_SERVE(mLatLng,
                                    RADIUS)))
                    ), new TypeToken<TrafficInfo<evaluation, road_traffic<congestion_sections>>>(){}.getType());
                    if (trafficInfo.getStatus() == 0){
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("HOME", trafficInfo.toString());
                                List<road_traffic> road_traffics = new ArrayList<>();
                                List<congestion_sections> congestion_sections = new ArrayList<>();
                                List<road_traffic> road_traffics1 = trafficInfo.getRoad_traffic();
                                for (int i = 0; i < road_traffics1.size(); i ++) {
                                    if (road_traffics1.get(i).getCongestion_sections() != null){
                                        road_traffics.add((road_traffic) road_traffics1.get(i));
                                    }
                                }
                                Log.d("HOME", road_traffics.toString());
//                                mListView.setAdapter(new TrafficAdapter(activity.getApplicationContext(), road_traffics));
                                for (int i = 0; i < road_traffics.size(); i ++){
                                    for (int j = 0; j < road_traffics.get(i).getCongestion_sections().size(); j ++){
                                        congestion_sections.add((com.qinwang.intelligence.tools.bean.congestion_sections) road_traffics.get(i).getCongestion_sections().get(j));
                                    }
                                }
                                evaluation mEvaluation = (evaluation) trafficInfo.getEvaluation();
                                if (congestion_sections.size() == 0){
                                    linearLayout.setVisibility(View.VISIBLE);
                                    textView_message.setText(mEvaluation.getStatus_desc());
                                }else {
                                    linearLayout.setVisibility(View.VISIBLE);
                                    textView_message.setText(mEvaluation.getStatus_desc());
                                    mListView.setAdapter(new BannerListAdapter(activity.getApplicationContext(), congestion_sections));
                                    Line.setGuidelinePercent((float)0.4);
                                }
                            }
                        });
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

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

    public static class ViewHolder{
        public TextView roadName;
        public ListView congestionListView;
    }

    public static class ViewHolder2{
        public TextView roadEva, roadSpeed, roadDis, roadTrend, roadDes;
    }
}
