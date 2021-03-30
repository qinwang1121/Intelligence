package com.qinwang.intelligence.main.fragment.upload;

import android.app.Activity;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qinwang.intelligence.R;
import com.qinwang.intelligence.tools.bean.Mistake;
import com.qinwang.intelligence.tools.bean.Response;
import com.qinwang.intelligence.tools.net.BaseAPI;
import com.qinwang.intelligence.tools.net.Utils;

import java.util.LinkedHashMap;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/3/25
 * @Description:com.qinwang.intelligence.main.fragment.upload
 * @Version:1.0
 * @function:
 */
public class UploadingModelImpl implements UploadingConstract.UploadingModel {
    @Override
    public void uploading(final Activity activity, String carNumber, String carColor, String carType, String boardColor,
                          String mistakeTime, String mistakePlace, final String mistakeDescribe, String policeName, final onUploadingListener<Mistake> listener) {
        final LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("carBoardID", carNumber);
        map.put("carColor", carColor);
        map.put("carBoardColor", boardColor);
        map.put("carType", carType);
        map.put("mistakeTime", mistakeTime);
        map.put("mistakePlace", mistakePlace);
        map.put("mistakeDescribe", mistakeDescribe);
        map.put("policeName", policeName);
        if (TextUtils.isEmpty(carNumber)){
            listener.onCarNumberError();
        }
        if (TextUtils.isEmpty(carColor)){
            listener.onCarColorError();
        }
        if (TextUtils.isEmpty(carType)){
            listener.onCarTypeError();
        }
        if(!TextUtils.isEmpty(carType)){
            listener.onCarType();
        }
        if (TextUtils.isEmpty(boardColor)){
            listener.onBoardColorError();
        }else {
            listener.onBoardColor();
        }
        if (TextUtils.isEmpty(mistakeDescribe)){
            listener.onDescribeError();
        }
        if(!TextUtils.isEmpty(carNumber) && !TextUtils.isEmpty(carColor) && !TextUtils.isEmpty(carType)
        && !TextUtils.isEmpty(boardColor) && !TextUtils.isEmpty(mistakeDescribe)){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        final Response<Mistake> mistakeResponse = new Gson().fromJson(new String(
                                Utils.postUtil(Utils.postSet(BaseAPI.URL_UPLOADING),
                                        map)),
                                new TypeToken<Response<Mistake>>(){}.getType());
                        if (mistakeResponse.isSuccess()){
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    listener.onSuccess(mistakeResponse.getMsg(),
                                            mistakeResponse.getData());
                                }
                            });
                        }else {
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    listener.onFail(String.valueOf(mistakeResponse.getStatus()),
                                            mistakeResponse.getMsg());
                                }
                            });
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                listener.onFail("", activity.getApplicationContext()
                                        .getString(R.string.uploading_fail));
                            }
                        });
                    }
                }
            }).start();
        }
    }
}
