package com.qinwang.intelligence.main.fragment.search;

import android.app.Activity;

import com.qinwang.base.BaseContract;
import com.qinwang.base.MVPLisenter;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/3/28
 * @Description:com.qinwang.intelligence.main.fragment.search
 * @Version:1.0
 * @function:
 */
public interface SearchContract {
    interface SearchView extends BaseContract.BaseView{
        void showSearchMsgError();

        void showMsgView();

        void hideMsgView();
    }

    interface SearchModel extends BaseContract.BaseModel{
        interface onSearchListener extends MVPLisenter{
            void onSearchMsgError();
        }
        void Search(Activity activity, String msg, onSearchListener listener);
    }

    interface SearchPresenter{

        void onDestroy();

        void validateSearch(String Msg);
    }
}
