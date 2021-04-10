package com.qinwang.intelligence.login;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.qinwang.base.BaseActivity;
import com.qinwang.intelligence.AppSelect;
import com.qinwang.intelligence.MyApplication;
import com.qinwang.intelligence.R;
import com.qinwang.intelligence.main.MainActivity;
import com.qinwang.intelligence.register.RegisterActivity;
import com.qinwang.intelligence.tools.WindowAssistant;

public class LoginActivity extends BaseActivity implements LoginContract.LoginView, View.OnClickListener {

    private static final String TAG = "LoginActivity";

    private LoginContract.LoginPresenter mLoginPresenter;

    private WindowAssistant windowAssistant = new WindowAssistant();
    public int width, height;

    private Button loginButton;
    private TextView register_text;
    private EditText userName_editText, password_editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        width = windowAssistant.getWindowWidth(this);
        height = windowAssistant.getWindowHeight(this);
        Log.i(TAG, "onCreate: width=" + width + ",height=" + height);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        if (AppSelect.getAppUser().equals("admin")){
            register_text.setVisibility(View.GONE);
        }else if (AppSelect.getAppUser().equals("user")){
            register_text.setVisibility(View.VISIBLE);
        }

        if(Build.VERSION.SDK_INT >= 23){
            int checkPermission = ContextCompat.checkSelfPermission(LoginActivity.this,
                    Manifest.permission.ACCESS_COARSE_LOCATION);
            if (checkPermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(LoginActivity.this,new
                        String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);return;
            }
        }

        mLoginPresenter = new LoginPresenterImpl(LoginActivity.this,
                this);

        if (!MyApplication.isFirstLogin){
            SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
//            userName_editText.setText(sharedPreferences.getString("userName", null));
//            password_editText.setText(sharedPreferences.getString("passWord", null));
            mLoginPresenter.validateLogin(sharedPreferences.getString("userName", null),
                    sharedPreferences.getString("passWord", null));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPresenter.onDestroy();
    }

    public void initView(){

        userName_editText = (EditText)findViewById(R.id.input_userName);
        password_editText = (EditText)findViewById(R.id.input_passwork);
        loginButton = (Button)findViewById(R.id.enter);
        register_text = (TextView)findViewById(R.id.register);

        ViewGroup.LayoutParams layoutParams = loginButton.getLayoutParams();
        layoutParams.width = (int)(width / 2);

        loginButton.setOnClickListener(this);
        register_text.setOnClickListener(this);
    }

    @Override
    public void showUserNameError() {
        userName_editText.setError(getString(R.string.usernameError));
    }

    @Override
    public void showPassWordError() {
        password_editText.setError(getString(R.string.passwordError));
    }

    @Override
    public void postToHome() {
        startActivity(new Intent(this,
                MainActivity.class));
        finish();
    }

    public void userRegister() {
        startActivity(new Intent(this,
                RegisterActivity.class));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.enter:
                mLoginPresenter.validateLogin(userName_editText.getText().toString(),
                        password_editText.getText().toString());
                break;
            case R.id.register:
                userRegister();
                break;
        }
    }
}