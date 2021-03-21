package com.qinwang.intelligence.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.qinwang.base.BaseActivity;
import com.qinwang.intelligence.AppSelect;
import com.qinwang.intelligence.R;
import com.qinwang.intelligence.tools.WindowAssistant;

public class LoginActivity extends BaseActivity implements LoginContract.LoginView {

    private static final String TAG = "LoginActivity";

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
    }

    public void initView(){

        userName_editText = (EditText)findViewById(R.id.input_userName);
        password_editText = (EditText)findViewById(R.id.input_passwork);
        loginButton = (Button)findViewById(R.id.enter);
        register_text = (TextView)findViewById(R.id.register);

        ViewGroup.LayoutParams layoutParams = loginButton.getLayoutParams();
        layoutParams.width = (int)(width / 2);
    }

    @Override
    public void showUserNameError() {

    }

    @Override
    public void showPassWordError() {

    }

    @Override
    public void postToHome() {

    }
}