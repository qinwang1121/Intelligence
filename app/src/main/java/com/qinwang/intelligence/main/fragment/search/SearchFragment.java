package com.qinwang.intelligence.main.fragment.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.qinwang.base.BaseFragment;
import com.qinwang.intelligence.R;

public class SearchFragment extends BaseFragment implements SearchContract.SearchView, View.OnClickListener {


    private EditText SearchMsg;
    private LinearLayout layoutSearch;

    private SearchContract.SearchPresenter mSearchPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        mSearchPresenter = new SearchPresenterImpl(getActivity(),
                this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSearchPresenter.onDestroy();
    }

    public void initView(){
        SearchMsg = (EditText)getView().findViewById(R.id.searchMsg);
        layoutSearch = (LinearLayout) getView().findViewById(R.id.layoutSearch);
        getView().findViewById(R.id.searchBut)
                .setOnClickListener(this);
        getView().findViewById(R.id.msgButBack)
                .setOnClickListener(this);
    }
    @Override
    public void showSearchMsgError() {
        SearchMsg.setError(getString(R.string.searchMsgError));
    }

    @Override
    public void showMsgView() {
        getView().findViewById(R.id.layoutMsg).setVisibility(View.VISIBLE);
        layoutSearch.setVisibility(View.GONE);
    }

    @Override
    public void hideMsgView() {
        getView().findViewById(R.id.layoutMsg).setVisibility(View.GONE);
        layoutSearch.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.searchBut:
                mSearchPresenter.validateSearch(SearchMsg.getText().toString());
                break;
            case R.id.msgButBack:
                hideMsgView();
                break;
        }
    }
}
