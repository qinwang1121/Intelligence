package com.qinwang.intelligence.login;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qinwang.base.BaseModelImpl;
import com.qinwang.intelligence.R;
import com.qinwang.intelligence.tools.bean.Response;
import com.qinwang.intelligence.tools.bean.User;
import com.qinwang.intelligence.tools.net.BaseAPI;
import com.qinwang.intelligence.tools.net.Utils;

import java.util.LinkedHashMap;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/3/21
 * @Description:com.qinwang.intelligence.login
 * @Version:1.0
 * @function:
 */
public class LoginModelImpl extends BaseModelImpl implements LoginContract.LoginModel{

    @Override
    public void onLogin(final Activity activity, final String userName, String passWord, final onLoginListener<User> listener) {
        final LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("userName", userName);
        map.put("passWord", passWord);
        if (TextUtils.isEmpty(userName)){
            listener.onUserNameError();
            listener.onFail("", activity.getApplicationContext().getString(R.string.usernameError));
        }if (TextUtils.isEmpty(passWord)) {
            listener.onPassWordError();
            listener.onFail("", activity.getApplicationContext().getString(R.string.passwordError));
        }else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        final Response<User> userResponse = new Gson().fromJson(new String(
                                        Utils.postUtil(Utils.postSet(BaseAPI.URL_LOGIN),
                                                map)),
                                new TypeToken<Response<User>>(){}.getType());
                        if (userResponse.isSuccess()){
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    listener.onSuccess(userResponse.getMsg(), userResponse.getData());
                                }
                            });
                        }else {
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    listener.onFail(String.valueOf(userResponse.getStatus()),
                                            userResponse.getMsg());
                                }
                            });
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                listener.onFail("null", activity.getApplicationContext().getString(R.string.netWorkError));
                            }
                        });
                    }
                }
            }).start();
        }
    }
}
