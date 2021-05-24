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
        findViewById(R.id.layoutUserName).setOnClickListener(this);
        safetyUserSex = findViewById(R.id.userGander);
        findViewById(R.id.laoutSex).setOnClickListener(this);
        safetyUserID = findViewById(R.id.userID);
        findViewById(R.id.layoutID).setOnClickListener(this);
        safetyPassWord = findViewById(R.id.userPassWord);
        findViewById(R.id.layoutPassWord).setOnClickListener(this);
        safetyTel = findViewById(R.id.userTel);
        findViewById(R.id.layoutTEL).setOnClickListener(this);
        safetyHome = findViewById(R.id.userHome);
        findViewById(R.id.layoutHome).setOnClickListener(this);

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
            case R.id.layoutUserName:
            case R.id.laoutSex:
            case R.id.layoutID:
                new CircleDialog.Builder(this)
                        .setTitle(getString(R.string.Dialog_title))
                        .setText(getString(R.string.Dialog_immutable))
                        .setPositive(getString(R.string.Dialog_true),null)
                        .show();
                break;
            case R.id.layoutPassWord:
                new CircleDialog.Builder(this)
                        .setTitle(getString(R.string.Dialog_passWordSet))
                        .setInputHint(getString(R.string.Dialog_passWordHint))
                        .setPositiveInput(getString(R.string.Dialog_true), new OnInputClickListener() {
                            @Override
                            public void onClick(String text, View v) {
                                if (TextUtils.isEmpty(text)){
                                    showToast(getString(R.string.Dialog_passWordNull));
                                    return;
                                }else if (text.equals(sharedPreferences.getString("passWord", null))){
                                    showToast(getString(R.string.Dialog_passWordError));
                                    return;
                                }
                                final LinkedHashMap<String, String> map = new LinkedHashMap<>();
                                map.put("passWord", text);
                                UpDate(safetyPassWord,"passWord", text,BaseAPI.URL_UPDATE_PASSWORD);
                            }
                        })
                        .setNegative(getString(R.string.Dialog_false), null)
                        .show();
                break;
            case R.id.layoutTEL:
                new CircleDialog.Builder(this)
                        .setTitle(getString(R.string.Dialog_TELdSet))
                        .setInputHint(getString(R.string.Dialog_TELHint))
                        .setPositiveInput(getString(R.string.Dialog_true), new OnInputClickListener() {
                            @Override
                            public void onClick(String text, View v) {
                                if (TextUtils.isEmpty(text)){
                                    showToast(getString(R.string.Dialog_TELNull));
                                    return;
                                }
                                final LinkedHashMap<String, String> map = new LinkedHashMap<>();
                                map.put("phone", text);
                                UpDate(safetyTel, "phone", text, BaseAPI.URL_UPDATE_TEL);
                            }
                        })
                        .setNegative(getString(R.string.Dialog_false), null)
                        .show();
                break;
            case R.id.layoutHome:
                new CircleDialog.Builder(this)
                        .setTitle(getString(R.string.Dialog_HomedSet))
                        .setInputHint(getString(R.string.Dialog_HomeHint))
                        .setPositiveInput(getString(R.string.Dialog_true), new OnInputClickListener() {
                            @Override
                            public void onClick(String text, View v) {
                                if (TextUtils.isEmpty(text)){
                                    showToast(getString(R.string.Dialog_HomeNull));
                                    return;
                                }
                                UpDate(safetyHome,"home", text, BaseAPI.URL_UPDATE_HOME);
                            }
                        })
                        .setNegative(getString(R.string.Dialog_false), null)
                        .show();
                break;
            case R.id.makeSure:
                finish();
                break;
        }
    }

    public void UpDate(final TextView textView, final String msgName, final String newMsg, final String url){
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
                                sharedPreferences.edit().putString(msgName, newMsg);
                                textView.setText(newMsg);
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