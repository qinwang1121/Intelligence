package com.qinwang.intelligence.main.fragment.myself.safety;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mylhyl.circledialog.CircleDialog;
import com.mylhyl.circledialog.view.listener.OnInputClickListener;
import com.qinwang.base.BaseActivity;
import com.qinwang.intelligence.R;
import com.qinwang.intelligence.tools.bean.Response;
import com.qinwang.intelligence.tools.bean.User;
import com.qinwang.intelligence.tools.net.BaseAPI;
import com.qinwang.intelligence.tools.net.Utils;

import java.util.LinkedHashMap;

public class SafetyActivity extends BaseActivity implements View.OnClickListener {

    private SharedPreferences sharedPreferences;
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
        safetyUserName.setOnClickListener(this);
        safetyUserSex = findViewById(R.id.userGander);
        safetyUserSex.setOnClickListener(this);
        safetyUserID = findViewById(R.id.userID);
        safetyUserID.setOnClickListener(this);
        safetyPassWord = findViewById(R.id.userPassWord);
        safetyPassWord.setOnClickListener(this);
        safetyTel = findViewById(R.id.userTel);
        safetyTel.setOnClickListener(this);
        safetyHome = findViewById(R.id.userHome);
        safetyHome.setOnClickListener(this);

        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
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
        switch (view.getId()){
            case R.id.userName:
            case R.id.userGander:
            case R.id.userID:
                new CircleDialog.Builder(this)
                        .setTitle("提示")
                        .setText("不可更改")
                        .setPositive("确定",null)
                        .show();
                break;
            case R.id.userPassWord:
                new CircleDialog.Builder(this)
                        .setTitle("修改密码")
                        .setInputHint("请输入新的密码")
                        .setPositiveInput("确定", new OnInputClickListener() {
                            @Override
                            public void onClick(String text, View v) {
                                if (TextUtils.isEmpty(text)){
                                    showToast("密码不能为空");
                                    return;
                                }else if (text.equals(sharedPreferences.getString("passWord", null))){
                                    showToast("不能与原始密码相同");
                                }
                                final LinkedHashMap<String, String> map = new LinkedHashMap<>();
                                map.put("passWord", text);
                                UpDate("passWord", text,BaseAPI.URL_UPDATE_PASSWORD);
                                sharedPreferences.edit().putString("passWord", text);
                                safetyPassWord.setText(text);
                            }
                        })
                        .setNegative("取消", null)
                        .show();
                break;
            case R.id.userTel:
                new CircleDialog.Builder(this)
                        .setTitle("电话号码修改")
                        .setInputHint("请输入新的电话号码")
                        .setPositiveInput("确定", new OnInputClickListener() {
                            @Override
                            public void onClick(String text, View v) {
                                if (TextUtils.isEmpty(text)){
                                    showToast("内容不能为空");
                                    return;
                                }
                                final LinkedHashMap<String, String> map = new LinkedHashMap<>();
                                map.put("phone", text);
                                UpDate("phone", text, BaseAPI.URL_UPDATE_TEL);
                                sharedPreferences.edit().putString("phone", text);
                                safetyTel.setText(text);
                            }
                        })
                        .setNegative("取消", null)
                        .show();
                break;
            case R.id.userHome:
                new CircleDialog.Builder(this)
                        .setTitle("家庭住址修改")
                        .setInputHint("请输入新的家庭住址")
                        .setPositiveInput("确定", new OnInputClickListener() {
                            @Override
                            public void onClick(String text, View v) {
                                if (TextUtils.isEmpty(text)){
                                    showToast("内容不能为空");
                                    return;
                                }
                                UpDate("home", text, BaseAPI.URL_UPDATE_HOME);
                                sharedPreferences.edit().putString("home", text);
                                safetyHome.setText(text);
                            }
                        })
                        .setNegative("取消", null)
                        .show();
                break;
            case R.id.makeSure:
                finish();
                break;
        }
    }

    public void UpDate(String msgName, String newMsg, final String url){
        final LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put(msgName,newMsg);
        map.put("id", sharedPreferences.getString("ID", ""));
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final Response<User> userResponse = new Gson().fromJson(new String(
                                    Utils.postUtil(Utils.postSet(url),
                                            map)),
                            new TypeToken<Response<User>>(){}.getType());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (userResponse.isSuccess()){
                                showToast(userResponse.getMsg());
                            }else {
                                showToast(userResponse.getMsg());
                            }
                        }
                    });
                }catch (final Exception e){
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showToast(String.valueOf(e));
                        }
                    });
                }
            }
        }).start();
    }
}