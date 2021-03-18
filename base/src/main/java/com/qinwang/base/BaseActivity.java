package com.qinwang.base;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.qinwang.base.tools.LoadingDialog;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/3/18
 * @Description:com.qinwang.base
 * @Version:1.0
 * @function:
 */
public class BaseActivity extends AppCompatActivity implements BaseContract.BaseView {

    private LoadingDialog mLoadingDialog = null;
    @Override
    public void showToast(String msg) {
        Toast.makeText(this,
                msg,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading(String tag, String msg) {
        if (mLoadingDialog == null){
            mLoadingDialog = new LoadingDialog(this,
                    R.style.Loading_Dialog);
            mLoadingDialog.setTitle(msg);
            mLoadingDialog.show();
        }
    }

    @Override
    public void hideLoading(String tag) {
        if (mLoadingDialog != null){
            mLoadingDialog.cancel();
            mLoadingDialog = null;
        }
    }
}
