package com.qinwang.intelligence.main.fragment.consume;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
import com.qinwang.intelligence.tools.bean.TrafficInfo;
import com.qinwang.intelligence.tools.bean.congestion_sections;
import com.qinwang.intelligence.tools.bean.evaluation;
import com.qinwang.intelligence.tools.bean.road_traffic;
import com.qinwang.intelligence.tools.net.BaseAPI;
import com.qinwang.intelligence.tools.net.Utils;

import java.util.ArrayList;
import java.util.List;

public class ConsumeFragment extends BaseFragment {

    private static int MAP_CLICK = 1;

    private static MapView mMapView = null;
    public static BaiduMap mBaiduMap;
    public static TextView cityName, roadName, roadMsg;
    private static ListView roadList;
    private static Activity activity = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_consume, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = getActivity();
        initView();
        setMap();
    }

    public void initView(){
        mMapView = getView().findViewById(R.id.mapView);
        mMapView.showZoomControls(false);   //隐藏缩放按钮

        cityName = getView().findViewById(R.id.cityName);
        roadName = getView().findViewById(R.id.roadName);
        roadMsg = getView().findViewById(R.id.roadMsg);
        roadList = getView().findViewById(R.id.roadList);
        roadList.setVisibility(View.GONE);
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
                getRoadMsg(activity, cityName.getText().toString(), roadName.getText().toString());
//                getRoadMsg(activity, "北京市", "东二环");
            }else {
                roadList.setVisibility(View.GONE);
//                Toast.makeText(activity, "xxxx", Toast.LENGTH_SHORT).show();
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

    public static void getRoadMsg(final Activity activity, final String city, final String roadName){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final TrafficInfo trafficInfo = new Gson().fromJson(new String(
                            Utils.getUtil(Utils.getSet(BaseAPI.USER_WEB_ROAD_SERVE(roadName, city)))
                    ), new TypeToken<TrafficInfo<evaluation, road_traffic<congestion_sections>>>(){}.getType());
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            Log.d("xxxxx", trafficInfo.getDescription());
//                            Log.d("xxxxx", String.valueOf(trafficInfo.getStatus()));
//                            Log.d("xxxxx", trafficInfo.getMessage()
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
                            roadMsg.setText(mEvaluation.getStatus_desc());
                            if (congestion_sections.size() == 0){
                                roadList.setVisibility(View.GONE);
                                roadMsg.setText(mEvaluation.getStatus_desc() + ",请放心通过");
                            }else {
                                roadList.setVisibility(View.VISIBLE);
                                roadList.setAdapter(new BannerListAdapter(activity.getApplicationContext(), congestion_sections));
                            }
                        }
                    });
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
}
