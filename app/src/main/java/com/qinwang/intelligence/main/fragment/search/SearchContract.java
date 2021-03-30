package com.qinwang.intelligence.main.fragment.search;

import android.app.Activity;

import com.qinwang.base.BaseContract;
import com.qinwang.base.MVPLisenter;
import com.qinwang.intelligence.tools.bean.CarMsg;
import com.qinwang.intelligence.tools.bean.Mistake;

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

        void showMsgView(CarMsg<Mistake> mistakeCarMsg);

        void hideMsgView();

    }

    interface SearchModel extends BaseContract.BaseModel{
        interface onSearchListener<CarMsg> extends MVPLisenter<CarMsg>{
            void onSearchMsgError();

        }
        void Search(Activity activity, String msg, onSearchListener<CarMsg<Mistake>> listener);
    }

    interface SearchPresenter{

        void onDestroy();

        void validateSearch(String Msg);
    }
}
