package com.qinwang.intelligence.register;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qinwang.base.BaseActivity;
import com.qinwang.intelligence.R;
import com.qinwang.intelligence.login.LoginActivity;
import com.qinwang.intelligence.tools.bean.Response;
import com.qinwang.intelligence.tools.bean.User;
import com.qinwang.intelligence.tools.net.BaseAPI;
import com.qinwang.intelligence.tools.net.Utils;

import java.util.LinkedHashMap;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private VideoView videoView;
    private EditText register_userName, register_passWork, register_repassWork, register_sex, register_userId, register_tel, register_aaddress;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    public void initView(){
        button = (Button)findViewById(R.id.register_register_button);
        button.setOnClickListener(this);
        register_userName = (EditText)findViewById(R.id.register_username_editText);
        register_passWork = (EditText)findViewById(R.id.register_password_editText);
        register_repassWork = (EditText)findViewById(R.id.register_rePassWord_editText);
        register_sex = (EditText)findViewById(R.id.register_sex_editText);
        register_userId = (EditText)findViewById(R.id.register_ID_editText);
        register_tel = (EditText)findViewById(R.id.register_tel_editText);
        register_aaddress = (EditText)findViewById(R.id.register_address_editText);

        videoView = (VideoView)findViewById(R.id.videoView);
        Provide();
    }

    public void Provide(){
        //设置播放加载路径
        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/raw/" + R.raw.register_bg));
        //播放
        videoView.start();
        //循环播放
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                videoView.start();
            }
        });
        //设置为静音
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setVolume(0f, 0f);
            }
        });
    }

    public void Register(final LinkedHashMap<String, String> map){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final Response<User> userResponse = new Gson().fromJson(new String(
                                    Utils.postUtil(Utils.postSet(BaseAPI.URL_REGISTER),
                                            map)),
                            new TypeToken<Response<User>>(){}.getType());
                    if (userResponse.isSuccess()){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showToast(userResponse.getMsg());
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            }
                        });
                    }else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showToast(userResponse.getMsg());
                            }
                        });
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showToast(getString(R.string.netWorkError));
                        }
                    });
                }
            }
        }).start();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.register_register_button){
            if (TextUtils.isEmpty(register_userName.getText().toString())){
                register_userName.setError("用户名不能为空");
                return;
            }
            if (TextUtils.isEmpty(register_passWork.getText().toString())){
                register_passWork.setError("密码不能为空");
                return;
            }
            if (TextUtils.isEmpty(register_repassWork.getText().toString())){
                register_repassWork.setError("确认密码不能为空");
            }
            if (! register_passWork.getText().toString().equals(register_repassWork.getText().toString())){
                register_repassWork.setError("两次密码输入不一致");
                return;
            }
            if (TextUtils.isEmpty(register_sex.getText().toString())){
                register_sex.setError("性别不能为空");
                return;
            }
            if (TextUtils.isEmpty(register_userId.getText().toString())){
                register_userId.setError("身份证号不能为空");
                return;
            }
            if (TextUtils.isEmpty(register_tel.getText().toString())){
                register_tel.setError("电话不能为空");
                return;
            }
            if (register_tel.getText().toString().length() != 11){
                register_tel.setError("手机号的长度只能为11位");
                Toast.makeText(RegisterActivity.this,
                        "手机号的长度只能为11位",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(register_aaddress.getText().toString())){
                register_aaddress.setError("现居住城市不能为空");
                return;
            }
            final LinkedHashMap<String, String> map = new LinkedHashMap<>();
            map.put("userName", register_userName.getText().toString());
            map.put("passWord", register_passWork.getText().toString());
            map.put("gender", register_sex.getText().toString());
            map.put("ID", register_userId.getText().toString());
            map.put("phone", register_tel.getText().toString());
            map.put("home", register_aaddress.getText().toString());
            map.put("power", "用户");
            Register(map);
        }
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        Provide();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Provide();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        Provide();
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        Provide();
    }

    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();
        Provide();
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Provide();
    }

}