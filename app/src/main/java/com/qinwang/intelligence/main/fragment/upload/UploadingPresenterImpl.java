package com.qinwang.intelligence.main.fragment.upload;

import android.app.Activity;
import android.view.View;

import com.qinwang.intelligence.R;
import com.qinwang.intelligence.tools.bean.Mistake;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/3/25
 * @Description:com.qinwang.intelligence.main.fragment.upload
 * @Version:1.0
 * @function:
 */
public class UploadingPresenterImpl implements UploadingConstract.UploadingPresenter, UploadingConstract.UploadingModel.onUploadingListener<Mistake>{

    private Activity mActivity;
    private UploadingConstract.UploadingModel mUploadingModel;
    private UploadingConstract.UploadingView mUploadingView;

    public UploadingPresenterImpl(Activity activity, UploadingConstract.UploadingView uploadingView){
        this.mActivity = activity;
        this.mUploadingView = uploadingView;
        this.mUploadingModel = new UploadingModelImpl();
    }
    @Override
    public void onDestroy() {
        mUploadingView = null;
    }

    @Override
    public void validateUploading(String carNumber, String carColor, String carType, String boardColor,
                                  String mistakeTime, String mistakePlace, String mistakeDescribe, String policeName) {
        if (mUploadingView != null){
            mUploadingView.showLoading("", mActivity.getApplicationContext().getString(R.string.Loading_upload));
        }
        mUploadingModel.uploading(mActivity, carNumber, carColor, carType, boardColor,
                mistakeTime, mistakePlace, mistakeDescribe, policeName, this);
    }

    @Override
    public void onCarNumberError() {
        if (mUploadingView != null){
            mUploadingView.showCarNumberError();
            mUploadingView.hideLoading("");
        }
    }

    @Override
    public void onCarColorError() {

        if (mUploadingView != null){
            mUploadingView.showCarColorError();
            mUploadingView.hideLoading("");
        }
    }

    @Override
    public void onCarTypeError() {
        if (mUploadingView != null){
            mUploadingView.showTypeError();
            mUploadingView.hideLoading("");
        }
    }

    @Override
    public void onCarType() {
        if (mUploadingView != null){
            mUploadingView.hideTypeError();
            mUploadingView.hideLoading("");
        }
    }

    @Override
    public void onBoardColorError() {
        if (mUploadingView != null){
            mUploadingView.showBoardColorError();
            mUploadingView.hideLoading("");
        }
    }

    @Override
    public void onBoardColor() {
        if (mUploadingView != null){
            mUploadingView.hideBoardColorError();
            mUploadingView.hideLoading("");
        }
    }

    @Override
    public void onDescribeError() {
        if (mUploadingView != null){
            mUploadingView.showDescribeError();
            mUploadingView.hideLoading("");
        }
    }

    @Override
    public void onSuccess(String msg, Mistake data) {
        if (mUploadingView != null){
            mUploadingView.hideLoading("");
            mUploadingView.showHintLog(msg,
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mUploadingView.empty();
                        }
                    });
        }
    }

    @Override
    public void onFail(String status, String msg) {
        if (mUploadingView != null){
            mUploadingView.showToast(msg);
            mUploadingView.hideLoading("");
        }
    }
}
