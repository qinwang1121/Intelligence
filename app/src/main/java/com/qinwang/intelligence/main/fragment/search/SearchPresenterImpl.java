package com.qinwang.intelligence.main.fragment.search;

import android.app.Activity;
import android.util.Log;

import com.qinwang.intelligence.R;
import com.qinwang.intelligence.tools.bean.CarMsg;
import com.qinwang.intelligence.tools.bean.Mistake;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/3/28
 * @Description:com.qinwang.intelligence.main.fragment.search
 * @Version:1.0
 * @function:
 */
public class SearchPresenterImpl implements SearchContract.SearchPresenter, SearchContract.SearchModel.onSearchListener<CarMsg<Mistake>> {

    private Activity mActivity;
    private SearchContract.SearchView mSearchView;
    private SearchContract.SearchModel mSearchModel;

    public SearchPresenterImpl (Activity activity, SearchContract.SearchView searchView){
        this.mActivity = activity;
        this.mSearchView = searchView;
        this.mSearchModel = new SearchModelImpl();
    }

    @Override
    public void onDestroy() {
        mSearchView = null;
    }

    @Override
    public void validateSearch(String Msg) {
        if (mSearchView != null){
            mSearchView.showLoading("",
                    mActivity.getApplicationContext().getString(R.string.Loading_search));
        }
        mSearchModel.Search(mActivity, Msg, this);
    }

    @Override
    public void onSearchMsgError() {
        if (mSearchView != null){
            mSearchView.showSearchMsgError();
        }
    }

    @Override
    public void onSuccess(String msg, CarMsg<Mistake> data) {
        if (mSearchView != null){
            mSearchView.showToast(msg);
            mSearchView.showMsgView(data);
            mSearchView.hideLoading("");
        }
    }

    @Override
    public void onFail(String status, String msg) {
        if (mSearchView != null){
            mSearchView.showToast(msg);
//            Log.d("SEARCH", msg);
            mSearchView.hideLoading("");
        }
    }
}
