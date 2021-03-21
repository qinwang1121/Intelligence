package com.qinwang.intelligence.main;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.qinwang.base.BaseActivity;
import com.qinwang.intelligence.BuildConfig;
import com.qinwang.intelligence.R;

public class MainActivity extends BaseActivity {

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
}