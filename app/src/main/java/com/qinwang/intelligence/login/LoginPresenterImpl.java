package com.qinwang.intelligence.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.qinwang.intelligence.MyApplication;
import com.qinwang.intelligence.R;
import com.qinwang.intelligence.tools.bean.User;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/3/21
 * @Description:com.qinwang.intelligence.login
 * @Version:1.0
 * @function:
 */
public class LoginPresenterImpl implements LoginContract.LoginPresenter{

    private Activity activity;
    private LoginContract.LoginView mLoginView;
    private LoginContract.LoginModel mLoginModel;

    public LoginPresenterImpl(Activity activity, LoginContract.LoginView loginView){
        this.activity = activity;
        this.mLoginView = loginView;
        mLoginModel = new LoginModelImpl();
    }

    @Override
    public void validateLogin(String userName, String passWord) {
        mLoginView.showLoading("",activity.getApplicationContext().getString(R.string.Loading_login));
        mLoginModel.onLogin(activity, userName, passWord, new LoginContract.LoginModel.onLoginListener<User>() {
            @Override
            public void onUserNameError() {
                mLoginView.showUserNameError();
            }

            @Override
            public void onPassWordError() {
                mLoginView.showPassWordError();
            }

            @Override
            public void onSuccess(String msg, User data) {
                MyApplication.isFirstLogin = false;
                mLoginView.postToHome();
                mLoginView.showToast(msg);
                mLoginView.hideLoading("");
                SharedPreferences sharedPreferences = activity.getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
                @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("userName", data.getUserName());
                editor.putString("passWord", data.getPassWord());
                editor.putString("gender", data.getGender());
                editor.putString("ID", data.getID());
                editor.putString("phone", data.getPhone());
                editor.putString("home", data.getHome());
                editor.putString("photo", data.getPhoto());
                editor.apply();
            }

            @Override
            public void onFail(String status, String msg) {
                mLoginView.showToast(msg);
                mLoginView.hideLoading("");
            }
        });
    }

    @Override
    public void onDestroy() {
        mLoginView = null;
    }
}
