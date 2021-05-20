package com.qinwang.intelligence.main;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.qinwang.base.BaseActivity;
import com.qinwang.intelligence.BuildConfig;
import com.qinwang.intelligence.MyApplication;
import com.qinwang.intelligence.R;
import com.qinwang.intelligence.main.fragment.consume.ConsumeFragment;
import com.qinwang.intelligence.main.fragment.home.HomeFragment;

public class MainActivity extends BaseActivity {

    //记录用户首次点击返回键的时间
    private long firstTime = 0;

    private MyLocationListener mBDAbstractLocationListener = new MyLocationListener();
    //定位模式
    private MyLocationConfiguration.LocationMode mLocationMode;
    private LocationClient mLocationClient;

    //坐标类型
    private static final String COORD_TYPE = "bd09ll";
    //定位间隔
    private static final int SCAN_SPAN = 1000;  //1000ms

    public static double ADMIN_LATITUDE = 0;
    public static double ADMIN_LONGITUDE = 0;
    public static String CITY_NAME = "北京";
    public static String ROAD_NAME = "东二环";
    private static final double INTERVAL = 500; //500m

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavController navController = Navigation.findNavController(this,
                R.id.fragment);
        if (BuildConfig.PURPOSE.equals("admin")){
            bottomNavigationView.inflateMenu(R.menu.menu_admin);
            navController.setGraph(R.navigation.admin_navigation);
        }else if (BuildConfig.PURPOSE.equals("user")){
            bottomNavigationView.inflateMenu(R.menu.menu_user);
            navController.setGraph(R.navigation.user_navigation);
        }
        AppBarConfiguration configuration = new AppBarConfiguration
                .Builder(bottomNavigationView.getMenu())
                .build();
        NavigationUI.setupActionBarWithNavController(this,
                navController,
                configuration);
        NavigationUI.setupWithNavController(bottomNavigationView,
                navController);

        initLocation();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * 使系统的返回键失效
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            return true;
        }
        return false;
    }

    /**
     * 双击退出应用的实现
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 2000) {
                Toast.makeText(MainActivity.this,
                        getString(R.string.back),
                        Toast.LENGTH_SHORT).show();
                firstTime = secondTime;
                return true;
            } else {
                finish();
            }
        }

        return super.onKeyUp(keyCode, event);
    }

    private void initLocation(){
        mLocationClient = new LocationClient(getApplicationContext());          //定位初始化
        mLocationClient.registerLocationListener(mBDAbstractLocationListener);  //注册LocationListener监听器
        //通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);                //打开GPS
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);   //设置定位模式，高精度，低功耗
        option.setCoorType(COORD_TYPE);         //设置定位坐标类型
        option.setScanSpan(SCAN_SPAN);          //1s定位一次
        option.setIsNeedAddress(true);          //地址信息
        option.setIsNeedLocationDescribe(true); //地址描述
        option.setNeedDeviceDirect(true);       //设备方向
        mLocationClient.setLocOption(option);   //保存定位参数与信息
        mLocationClient.start();
    }

    /**
     * 定位回调
     */
    class MyLocationListener extends BDAbstractLocationListener{
        private static final String TAG = "LocationListener_message";

        @SuppressLint("LongLogTag")
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {

            double latitude = bdLocation.getLatitude();         //获取纬度信息
            double longitude = bdLocation.getLongitude();       //获取经度信息
            String addr = bdLocation.getAddrStr();              //获取详细地址信息
            String country = bdLocation.getCountry();           //获取国家
            String province = bdLocation.getProvince();         //获取省份
            String city = bdLocation.getCity();                 //获取城市
            String district = bdLocation.getDistrict();         //获取区县
            String street = bdLocation.getStreet();             //获取街道信息
            String adcode = bdLocation.getAdCode();             //获取adcode
            String town = bdLocation.getTown();                 //获取乡镇信息
            float radius = bdLocation.getRadius();              //获取定位精度，默认值为0.0f
            int errorCode = bdLocation.getLocType();            //错误码

            if (DistanceUtil.getDistance(new LatLng(latitude, longitude), new LatLng(ADMIN_LATITUDE, ADMIN_LONGITUDE)) > INTERVAL){
                ADMIN_LATITUDE = latitude;
                ADMIN_LONGITUDE = longitude;
//                HomeFragment.getMsg(MainActivity.this, new LatLng(ADMIN_LATITUDE, ADMIN_LONGITUDE));
            }
            if (city != CITY_NAME || street != ROAD_NAME){
                CITY_NAME = city;
                ROAD_NAME = street;
                ConsumeFragment.cityName.setText(CITY_NAME);
                ConsumeFragment.roadName.setText(ROAD_NAME);
            }
            if (bdLocation == null){
                return;
            }
            MyLocationData myLocationData = new MyLocationData.Builder()
                    .accuracy(radius)
                    .direction(bdLocation.getDirection())
                    .latitude(latitude)
                    .longitude(longitude)
                    .build();

//            Log.d(TAG,"当前位置信息：" + addr);
            //如果是第一次定位,就定位到以自己为中心
            if (MyApplication.isFirstLoc){
                LatLng ll = new LatLng(latitude, longitude);                            //获取用户当前经纬度
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);               //更新坐标位置
                MyApplication.isFirstLoc = false;//取消第一次定位
                if(BuildConfig.PURPOSE.equals("admin")){
                    HomeFragment.mBaiduMap.setMyLocationData(myLocationData);
                    HomeFragment.mBaiduMap.animateMapStatus(u);                             //设置地图位置
                }else {
                    ConsumeFragment.mBaiduMap.setMyLocationData(myLocationData);
                    ConsumeFragment.mBaiduMap.animateMapStatus(u);                             //设置地图位置
                    ConsumeFragment.getRoadMsg(MainActivity.this, city, street);
//                    ConsumeFragment.getRoadMsg(MainActivity.this, "北京市", "东二环");
                }
            }
        }

        /**
         *定位失败原因
         *
         * @param i     返回值
         * @param i1    类型
         * @param s     问题排查策略
         */
        @SuppressLint("LongLogTag")
        @Override
        public void onLocDiagnosticMessage(int i, int i1, String s) {
            super.onLocDiagnosticMessage(i, i1, s);
            Log.e(TAG,"返回值：" + i + "类型：" + i1
                    + "\n问题策略：" + s);
        }
    }
}