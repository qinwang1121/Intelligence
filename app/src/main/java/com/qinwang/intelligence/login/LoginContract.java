package com.qinwang.intelligence.login;

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

        interface onLoginLisente extends MVPLisenter{

            void onUserNameError();

            void onPassWordError();
        }

        void onLogin(String userName, String passWord, onLoginLisente lisente);
    }

    interface LoginPresenter{

        void validateLogin(String userName, String passWord);

        void onDestroy();
    }
}
