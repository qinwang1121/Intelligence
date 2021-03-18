package com.qinwang.base;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/3/18
 * @Description:com.qinwang.base
 * @Version:1.0
 * @function:
 */
public interface BaseContract {

    interface BaseView{
        void showToast(String msg);

        void showLoading(String tag, String msg);

        void hideLoading(String tag);
    }

    interface BaseModel{

    }

    interface BasePresenter{

    }
}
