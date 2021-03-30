package com.qinwang.intelligence.main.fragment.myself.feedback;

import android.app.Activity;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qinwang.base.BaseModelImpl;
import com.qinwang.intelligence.R;
import com.qinwang.intelligence.tools.bean.FeedBack;
import com.qinwang.intelligence.tools.bean.Response;
import com.qinwang.intelligence.tools.net.BaseAPI;
import com.qinwang.intelligence.tools.net.Utils;

import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/3/21
 * @Description:com.qinwang.intelligence.fragment.myself.feedback
 * @Version:1.0
 * @function:
 */
public class FeedbackModelImpl extends BaseModelImpl implements FeedbackContract.FeedbackModel {
    @Override
    public void Feedback(final Activity activity, String Email, String message, final onFeedbackListener<FeedBack> listener) {
        final LinkedHashMap<String, String>map = new LinkedHashMap<>();
        if (TextUtils.isEmpty(message)){
            listener.onDescribeError(activity.getApplicationContext()
                    .getString(R.string.feedback_describe_error));
        }
        if (TextUtils.isEmpty(Email)){
            listener.onEmailError(activity.getApplicationContext()
                    .getString(R.string.feedback_mail_null));
        }
        if (! TextUtils.isEmpty(Email) && !isEmail(Email)){
            listener.onEmailError(activity.getApplicationContext()
                    .getString(R.string.feedback_mail_error));
        }else {
            map.put("Email", Email);
            map.put("message", message);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        final Response<FeedBack> feedBackResponse = new Gson().fromJson(new String(
                                Utils.postUtil(Utils.postSet(BaseAPI.URL_FEEDBACK),
                                        map)),
                                new TypeToken<Response<FeedBack>>(){}.getType());
                        if (feedBackResponse.isSuccess()){
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    listener.onSuccess(feedBackResponse.getMsg(),
                                            feedBackResponse.getData());
                                }
                            });
                        }else {
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    listener.onFail("", feedBackResponse.getMsg());
                                }
                            });
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                listener.onFail("", activity.getApplicationContext().getString(R.string.netWorkError));
                            }
                        });
                    }
                }
            }).start();
        }
    }

    /**
     * 正则表达式 判断邮箱格式是否正确
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }
}
