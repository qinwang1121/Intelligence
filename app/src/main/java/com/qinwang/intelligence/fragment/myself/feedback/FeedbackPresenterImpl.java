package com.qinwang.intelligence.fragment.myself.feedback;

import android.app.Activity;
import android.view.View;

import com.qinwang.intelligence.R;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/3/21
 * @Description:com.qinwang.intelligence.fragment.myself.feedback
 * @Version:1.0
 * @function:
 */
public class FeedbackPresenterImpl implements FeedbackContract.FeedbackPresenter, FeedbackContract.FeedbackModel.onFeedbackListener {

    private Activity mActivity;
    private FeedbackContract.FeedbackView mFeedbackView;
    private FeedbackContract.FeedbackModel mFeedbackModel;

    public FeedbackPresenterImpl(Activity activity, FeedbackContract.FeedbackView feedbackView){
        this.mActivity = activity;
        this.mFeedbackView = feedbackView;
        mFeedbackModel = new FeedbackModelImpl();
    }

    @Override
    public void onDestroy() {
        mFeedbackView = null;
    }

    @Override
    public void validateFeedback(Activity activity, String Email, String message) {
        mFeedbackView.showLoading("", mActivity.getApplicationContext()
                .getString(R.string.Loading_upload));
        mFeedbackModel.Feedback(activity, Email, message, this);
    }

    @Override
    public void onDescribeError(String msg) {
        if (mFeedbackView != null){
            mFeedbackView.showDescribeError();
            mFeedbackView.hideLoading("");
        }
    }

    @Override
    public void onEmailError(String msg) {
        mFeedbackView.showEmailError(msg);
        mFeedbackView.hideLoading("");
    }

    @Override
    public void onSuccess(String msg) {
        mFeedbackView.showToast(msg);
        mFeedbackView.hideLoading("");
        mFeedbackView.showHintDialog(msg,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mFeedbackView.closeActivity();
                    }
                });
    }

    @Override
    public void onFail(String status, String msg) {
        mFeedbackView.showToast(msg);
        mFeedbackView.hideLoading("");
    }
}
