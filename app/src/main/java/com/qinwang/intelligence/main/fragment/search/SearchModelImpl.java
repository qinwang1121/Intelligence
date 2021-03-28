package com.qinwang.intelligence.main.fragment.search;

import android.app.Activity;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.qinwang.base.BaseModelImpl;
import com.qinwang.intelligence.R;
import com.qinwang.intelligence.tools.bean.CarMsg;
import com.qinwang.intelligence.tools.bean.Mistake;
import com.qinwang.intelligence.tools.bean.Response;
import com.qinwang.intelligence.tools.net.BaseAPI;
import com.qinwang.intelligence.tools.net.Utils;

import java.util.LinkedHashMap;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/3/28
 * @Description:com.qinwang.intelligence.main.fragment.search
 * @Version:1.0
 * @function:
 */
public class SearchModelImpl extends BaseModelImpl implements SearchContract.SearchModel {

    @Override
    public void Search(final Activity activity, String msg, final onSearchListener listener) {

        final LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("ID", msg);
        if (TextUtils.isEmpty(msg)){
            listener.onSearchMsgError();
        }else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        final Response<CarMsg<Mistake>> carMsgResponse = new Gson().fromJson(new String(
                                Utils.postUtil(Utils.postSet(BaseAPI.URL_SEARCH),
                                        map)),
                                Response.class);
                        if (carMsgResponse.isSuccess()){
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    listener.onSuccess(carMsgResponse.getMsg());
                                }
                            });
                        }else {
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    listener.onFail(String.valueOf(carMsgResponse.getStatus()),
                                            carMsgResponse.getMsg());
                                }
                            });
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                listener.onFail("",
                                        activity.getApplicationContext().getString(R.string.searchFail));
                            }
                        });
                    }
                }
            }).start();
        }
    }
}
