package com.qinwang.intelligence.main.fragment.upload;

import android.app.Activity;
import android.view.View;

import com.qinwang.base.BaseContract;
import com.qinwang.base.MVPLisenter;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/3/25
 * @Description:com.qinwang.intelligence.main.fragment.upload
 * @Version:1.0
 * @function:
 */
public interface UploadingConstract {
    interface UploadingView extends BaseContract.BaseView {
        void showCarNumberError();

        void showCarColorError();

        void showTypeError();

        void hideTypeError();

        void showBoardColorError();

        void hideBoardColorError();

        void showDescribeError();

        void showHintLog(String msg, View.OnClickListener listener);

        void empty();
    }

    interface UploadingModel extends BaseContract.BaseModel{
        interface onUploadingListener extends MVPLisenter{
            void onCarNumberError();

            void onCarColorError();

            void onCarTypeError();

            void onCarType();

            void onBoardColorError();

            void onBoardColor();

            void onDescribeError();
        }

        void uploading(Activity activity, String carNumber, String carColor, String carType,
                       String boardColor, String mistakeTime, String mistakePlace,
                       String mistakeDescribe, String policeName, onUploadingListener listener);
    }

    interface UploadingPresenter{
        void onDestroy();

        void validateUploading(String carNumber, String carColor, String carType,
                               String boardColor, String mistakeTime, String mistakePlace,
                               String mistakeDescribe, String policeName);
    }
}
