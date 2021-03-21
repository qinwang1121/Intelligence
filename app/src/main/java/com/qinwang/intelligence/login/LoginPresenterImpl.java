package com.qinwang.intelligence.login;

import android.app.Activity;

import com.qinwang.intelligence.R;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/3/21
 * @Description:com.qinwang.intelligence.login
 * @Version:1.0
 * @function:
 */
public class LoginPresenterImpl implements LoginContract.LoginPresenter, LoginContract.LoginModel.onLoginListener {

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
        mLoginModel.onLogin(activity, userName, passWord, this);
    }

    @Override
    public void onDestroy() {
        mLoginView = null;
    }

    @Override
    public void onUserNameError() {
        mLoginView.showUserNameError();
    }

    @Override
    public void onPassWordError() {
        mLoginView.showPassWordError();
    }

    @Override
    public void onSuccess(String msg) {
        mLoginView.postToHome();
        mLoginView.showToast(msg);
        mLoginView.hideLoading("");
    }

    @Override
    public void onFail(String status, String msg) {
        mLoginView.showToast(msg);
        mLoginView.hideLoading("");
    }
}
