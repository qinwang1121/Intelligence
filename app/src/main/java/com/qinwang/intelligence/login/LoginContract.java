package com.qinwang.intelligence.login;

import android.app.Activity;

import com.qinwang.base.BaseContract;
import com.qinwang.base.MVPLisenter;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/3/18
 * @Description:com.qinwang.intelligence.login
 * @Version:1.0
 * @function:
 */
public interface LoginContract {

    interface LoginView extends BaseContract.BaseView{

        void showUserNameError();

        void showPassWordError();

        void postToHome();
    }

    interface LoginModel extends BaseContract.BaseModel {

        interface onLoginListener extends MVPLisenter{

            void onUserNameError();

            void onPassWordError();
        }

        void onLogin(Activity activity, String userName, String passWord, onLoginListener listener);
    }

    interface LoginPresenter{

        void validateLogin(String userName, String passWord);

        void onDestroy();
    }
}
