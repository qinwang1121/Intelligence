package com.qinwang.intelligence.main.fragment.myself;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mylhyl.circledialog.CircleDialog;
import com.qinwang.base.BaseFragment;
import com.qinwang.intelligence.MyApplication;
import com.qinwang.intelligence.R;
import com.qinwang.intelligence.main.fragment.myself.feedback.FeedbackActivity;
import com.qinwang.intelligence.main.fragment.myself.safety.SafetyActivity;
import com.qinwang.intelligence.login.LoginActivity;

public class MyselfFragment extends BaseFragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_myself, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().findViewById(R.id.myself_safety)
                .setOnClickListener(this);
        getView().findViewById(R.id.myself_feedback)
                .setOnClickListener(this);
        getView().findViewById(R.id.myself_examine)
                .setOnClickListener(this);
        getView().findViewById(R.id.myself_out)
                .setOnClickListener(this);

        initView();
    }

    public void initView(){

        TextView userName = getView().findViewById(R.id.myself_realName_text);
        TextView userID = getView().findViewById(R.id.myself_userId_text);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        userName.setText(sharedPreferences.getString("userName", null));
        userID.setText(sharedPreferences.getString("ID", null));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.myself_safety:
                startActivity(new Intent(getActivity(),
                        SafetyActivity.class));
                break;
            case R.id.myself_feedback:
                startActivity(new Intent(getActivity(),
                        FeedbackActivity.class));
                break;
            case R.id.myself_examine:
                showExamineDialog();
                break;
            case R.id.myself_out:
                showExitDialog();
                break;
        }
    }

    public void showExamineDialog() {
        new CircleDialog.Builder(getActivity())
                .setTitle(getString(R.string.Dialog_title))
                .setText(getString(R.string.Dialog_Examine_text))
                .setPositive(getString(R.string.Dialog_true), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                })
                .show();
    }

    public void showExitDialog() {
        new CircleDialog.Builder(getActivity())
                .setTitle(getString(R.string.Dialog_title))
                .setText(getString(R.string.Dialog_text))
                .setPositive(getString(R.string.Dialog_true), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getActivity().finish();
                        MyApplication.isFirstLogin = true;
                        startActivity(new Intent(getActivity(),
                                LoginActivity.class));
                    }
                })
                .setNegative(getString(R.string.Dialog_false),null)
                .show();
    }
}
