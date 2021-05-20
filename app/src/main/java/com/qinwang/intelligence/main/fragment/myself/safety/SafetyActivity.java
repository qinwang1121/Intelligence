package com.qinwang.intelligence.main.fragment.myself.safety;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.qinwang.base.BaseActivity;
import com.qinwang.intelligence.R;

public class SafetyActivity extends BaseActivity implements View.OnClickListener {

    private TextView toolbar_title, safetyUserName, safetyUserSex, safetyUserID,
            safetyPassWord, safetyTel, safetyHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety);

        initView();
    }

    public void initView(){
        toolbar_title = (TextView)findViewById(R.id.toolber_titlle);

        toolbar_title.setText(getString(R.string.safetyTitle));
        toolbar_title.setGravity(Gravity.CENTER);

        safetyUserName = findViewById(R.id.userName);
        safetyUserSex = findViewById(R.id.userGander);
        safetyUserID = findViewById(R.id.userID);
        safetyPassWord = findViewById(R.id.userPassWord);
        safetyTel = findViewById(R.id.userTel);
        safetyHome = findViewById(R.id.userHome);

        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        safetyUserName.setText(sharedPreferences.getString("userName", null));
        safetyUserSex.setText(sharedPreferences.getString("gender", null));
        safetyUserID.setText(sharedPreferences.getString("ID", null));
        safetyPassWord.setText(sharedPreferences.getString("passWord", null));
        safetyPassWord.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        safetyTel.setText(sharedPreferences.getString("phone", null));
        safetyHome.setText(sharedPreferences.getString("home", null));

        findViewById(R.id.makeSure).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.makeSure){
            finish();
        }
    }
}