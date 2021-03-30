package com.qinwang.intelligence.main.fragment.search;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.qinwang.base.BaseFragment;
import com.qinwang.intelligence.R;
import com.qinwang.intelligence.tools.bean.CarMsg;
import com.qinwang.intelligence.tools.bean.Mistake;

public class SearchFragment extends BaseFragment implements SearchContract.SearchView, View.OnClickListener {


    private EditText SearchMsg;
    private LinearLayout layoutSearch;

    private TextView msgUserName, msgUserSex, msgUserID,
            msgCarNumber, msgCarName, msgCarType, msgCarColor;
    private ListView msgMistakeList;

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

        msgUserName = getView().findViewById(R.id.msgUserName);
        msgUserSex = getView().findViewById(R.id.msgUserSex);
        msgUserID = getView().findViewById(R.id.msgUserID);
        msgCarNumber = getView().findViewById(R.id.msgCarNumber);
        msgCarName = getView().findViewById(R.id.msgCarName);
        msgCarType = getView().findViewById(R.id.msgCarType);
        msgCarColor = getView().findViewById(R.id.msgCarColor);
        msgMistakeList = getView().findViewById(R.id.msgMistakeList);

        getView().findViewById(R.id.searchBut)
                .setOnClickListener(this);
        getView().findViewById(R.id.msgButBack)
                .setOnClickListener(this);
    }
    @Override
    public void showSearchMsgError() {
        SearchMsg.setError(getString(R.string.searchMsgError));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showMsgView(CarMsg<Mistake> mistakeCarMsg) {
        getView().findViewById(R.id.layoutMsg).setVisibility(View.VISIBLE);
        layoutSearch.setVisibility(View.GONE);

        msgUserName.setText(mistakeCarMsg.getCarUserName());
        msgUserSex.setText(mistakeCarMsg.getCarUserGender());
        msgUserID.setText(mistakeCarMsg.getCarUserId());
        msgCarNumber.setText(mistakeCarMsg.getCarBoardID() + "(" + mistakeCarMsg.getCarBoardColor() + ")");
        msgCarName.setText(mistakeCarMsg.getCarName());
        msgCarType.setText(mistakeCarMsg.getCarType());
        msgCarColor.setText(mistakeCarMsg.getCarColor());

        MyAdapter mMyAdapter = new MyAdapter(getActivity(), mistakeCarMsg.getList());
        msgMistakeList.setAdapter(mMyAdapter);
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
