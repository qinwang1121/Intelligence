package com.qinwang.intelligence.main.fragment.myself.feedback;

import android.app.Activity;
import android.view.View;

import com.qinwang.base.BaseContract;
import com.qinwang.base.MVPLisenter;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/3/21
 * @Description:com.qinwang.intelligence.fragment.myself.feedback
 * @Version:1.0
 * @function:
 */
public interface FeedbackContract {

    interface FeedbackView extends BaseContract.BaseView {
        void showDescribeError();

        void showHintDialog(String msg, View.OnClickListener listener);

        void showEmailError(String msg);

        void closeActivity();
    }

    interface FeedbackModel{
        interface onFeedbackListener extends MVPLisenter {
            void onDescribeError(String msg);

            void onEmailError(String msg);
        }

        void Feedback(Activity activity, String Email, String message, onFeedbackListener listener);
    }

    interface FeedbackPresenter{
        void onDestroy();

        void validateFeedback(Activity activity, String Email, String message);
    }
}
