package com.qinwang.base;

import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.qinwang.base.tools.LoadingDialog;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/3/18
 * @Description:com.qinwang.base
 * @Version:1.0
 * @function:
 */
public class BaseFragment extends Fragment implements BaseContract.BaseView {

    private LoadingDialog mLoadingDialog = null;
    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(),
                msg,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading(String tag, String msg) {
        if (mLoadingDialog == null){
            mLoadingDialog = new LoadingDialog(getContext(),
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
