package com.qinwang.intelligence.main;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.qinwang.base.BaseActivity;
import com.qinwang.intelligence.BuildConfig;
import com.qinwang.intelligence.R;

public class MainActivity extends BaseActivity {

    //记录用户首次点击返回键的时间
    private long firstTime = 0;

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
}