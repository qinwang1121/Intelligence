package com.qinwang.intelligence.main.fragment.upload;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mylhyl.circledialog.CircleDialog;
import com.qinwang.base.BaseFragment;
import com.qinwang.intelligence.R;
import com.qinwang.intelligence.main.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class UploadFragment extends BaseFragment implements UploadingConstract.UploadingView, View.OnClickListener {

    private final String TAG = "UploadingFragment";

    public static EditText carNumber, carColor, otherType,
            mistakeTime, mistakePlace,
            mistakeDescribe, policeName;
    private LinearLayout layoutOtherType;
    private CheckBox carBig, carSmall, truckBig, truckSmall,
            motorcycle, other,
            colorBlue, colorYellow, colorGreen, colorWhite, colorBlack;
    private TextView spaceType, spaceColor;

    private UploadingConstract.UploadingPresenter mUploadingPresenter;

    private String carType = null, boardColor = null;

    private boolean isClick = false;

    private Handler handler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_upload, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        getTime();
        carTypeListen();
        boardColorListen();

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        policeName.setText(sharedPreferences.getString("userName", null));

        mUploadingPresenter = new UploadingPresenterImpl(getActivity(), this);
    }

    public void initView(){

        carNumber = getView().findViewById(R.id.carNumber);
        carColor = getView().findViewById(R.id.carColor);
        otherType = getView().findViewById(R.id.otherType);
        mistakeTime = getView().findViewById(R.id.mistakeTime);
        mistakePlace = getView().findViewById(R.id.mistake_place);
        mistakePlace.setText(MainActivity.address);
        mistakeDescribe = getView().findViewById(R.id.mistake_describe);
        policeName = getView().findViewById(R.id.policeName);

        spaceType = getView().findViewById(R.id.spaceType);
        spaceColor = getView().findViewById(R.id.spaceColor);

        layoutOtherType = getView().findViewById(R.id.layout0therType);

        carBig = getView().findViewById(R.id.carBig);
        carSmall = getView().findViewById(R.id.carSmall);
        truckBig = getView().findViewById(R.id.truckBig);
        truckSmall = getView().findViewById(R.id.truckSmall);
        motorcycle = getView().findViewById(R.id.motorcycle);
        other = getView().findViewById(R.id.other);

        colorBlue = getView().findViewById(R.id.colorBlue);
        colorYellow = getView().findViewById(R.id.colorYellow);
        colorGreen = getView().findViewById(R.id.colorGreen);
        colorWhite = getView().findViewById(R.id.colorWhite);
        colorBlack = getView().findViewById(R.id.colorBlack);

        getView().findViewById(R.id.button_uploading)
                .setOnClickListener(this);

        mistakeTime.setFocusable(false);//设置为不可编辑
        mistakePlace.setFocusable(false);
        policeName.setFocusable(false);

    }

    public void getCarType(String type) {
        carType = type;
        Log.d(TAG, carType);
    }

    public void getBoardColor(String color) {
        boardColor = color;
        Log.d(TAG, boardColor);
    }

    public void carTypeListen(){
        carBig.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    carSmall.setChecked(false);
                    truckBig.setChecked(false);
                    truckSmall.setChecked(false);
                    motorcycle.setChecked(false);
                    other.setChecked(false);
                    getCarType(carBig.getText().toString());
                }else {
                    getCarType("");
                }
            }
        });
        carSmall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    carBig.setChecked(false);
                    truckBig.setChecked(false);
                    truckSmall.setChecked(false);
                    motorcycle.setChecked(false);
                    other.setChecked(false);
                    getCarType(carSmall.getText().toString());
                }else {
                    getCarType("");
                }
            }
        });
        truckBig.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    carBig.setChecked(false);
                    carSmall.setChecked(false);
                    truckSmall.setChecked(false);
                    motorcycle.setChecked(false);
                    other.setChecked(false);
                    getCarType(truckBig.getText().toString());
                }else {
                    getCarType("");
                }
            }
        });
        truckSmall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    carBig.setChecked(false);
                    carSmall.setChecked(false);
                    truckBig.setChecked(false);
                    motorcycle.setChecked(false);
                    other.setChecked(false);
                    getCarType(truckSmall.getText().toString());
                }else {
                    getCarType("");
                }
            }
        });
        motorcycle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    carBig.setChecked(false);
                    carSmall.setChecked(false);
                    truckBig.setChecked(false);
                    truckSmall.setChecked(false);
                    other.setChecked(false);
                    getCarType(motorcycle.getText().toString());
                }else {
                    getCarType("");
                }
            }
        });
        other.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    carBig.setChecked(false);
                    carSmall.setChecked(false);
                    truckBig.setChecked(false);
                    truckSmall.setChecked(false);
                    motorcycle.setChecked(false);
                    showLayoutOtherType();
                    isClick = true;
                }else {
                    hideLayoutOtherType();
                }
            }
        });
    }

    public void boardColorListen(){
        colorBlue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    colorYellow.setChecked(false);
                    colorGreen.setChecked(false);
                    colorWhite.setChecked(false);
                    colorBlack.setChecked(false);
                    getBoardColor(colorBlue.getText().toString());
                }else {
                    getBoardColor("");
                }
            }
        });
        colorYellow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    colorBlue.setChecked(false);
                    colorGreen.setChecked(false);
                    colorWhite.setChecked(false);
                    colorBlack.setChecked(false);
                    getBoardColor(colorYellow.getText().toString());
                }else {
                    getBoardColor("");
                }
            }
        });
        colorGreen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    colorBlue.setChecked(false);
                    colorYellow.setChecked(false);
                    colorWhite.setChecked(false);
                    colorBlack.setChecked(false);
                    getBoardColor(colorGreen.getText().toString());
                }else {
                    getBoardColor("");
                }
            }
        });
        colorWhite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    colorBlue.setChecked(false);
                    colorYellow.setChecked(false);
                    colorGreen.setChecked(false);
                    colorBlack.setChecked(false);
                    getBoardColor(colorWhite.getText().toString());
                }else {
                    getBoardColor("");
                }
            }
        });
        colorBlack.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    colorBlue.setChecked(false);
                    colorYellow.setChecked(false);
                    colorGreen.setChecked(false);
                    colorWhite.setChecked(false);
                    getBoardColor(colorBlack.getText().toString());
                }else {
                    getBoardColor("");
                }
            }
        });
    }

    public void showLayoutOtherType() {
        layoutOtherType.setVisibility(View.VISIBLE);
        getCarType(otherType.getText().toString());
    }

    public void hideLayoutOtherType() {
        layoutOtherType.setVisibility(View.GONE);
        otherType.setText(null);
        getCarType("");
    }

    @SuppressLint("HandlerLeak")
    public void getTime(){
        handler = new Handler(){
            @SuppressLint("HandlerLeak")
            @Override
            public void handleMessage(@NonNull Message msg) {
                mistakeTime.setText(String.valueOf(msg.obj));
            }
        };
        Threads mThreads = new Threads();
        mThreads.start();
    }

    @Override
    public void showCarNumberError() {
        carNumber.setError(getString(R.string.errorCarNumber));
    }

    @Override
    public void showCarColorError() {
        carColor.setError(getString(R.string.errorCarColor));
    }

    @Override
    public void showTypeError() {
        spaceType.setError(getString(R.string.errorType));
    }

    @Override
    public void hideTypeError() {
        spaceType.setError(null);
    }

    @Override
    public void showBoardColorError() {
        spaceColor.setError(getString(R.string.errorBoardColor));
    }

    @Override
    public void hideBoardColorError() {
        spaceColor.setError(null);
    }

    @Override
    public void showDescribeError() {
        mistakeDescribe.setError(getString(R.string.errorDescribe));
    }

    @Override
    public void showHintLog(String msg, View.OnClickListener listener) {
        new CircleDialog.Builder(getActivity())
                .setTitle(getString(R.string.Dialog_title))
                .setText(msg)
                .setPositive(getString(R.string.Dialog_true), listener)
                .show();
    }

    @Override
    public void empty() {
        carNumber.setText(null);
        carColor.setText(null);

        carBig.setChecked(false);
        carSmall.setChecked(false);
        truckBig.setChecked(false);
        truckSmall.setChecked(false);
        motorcycle.setChecked(false);
        other.setChecked(false);

        colorBlue.setChecked(false);
        colorYellow.setChecked(false);
        colorGreen.setChecked(false);
        colorWhite.setChecked(false);
        colorBlack.setChecked(false);

        mistakeDescribe.setText(null);

        getCarType("");
        getBoardColor("");
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_uploading){
            if (isClick){
                mUploadingPresenter.validateUploading(carNumber.getText().toString(),
                        carColor.getText().toString(),
                        otherType.getText().toString(),
                        boardColor,
                        mistakeTime.getText().toString(),
                        mistakePlace.getText().toString(),
                        mistakeDescribe.getText().toString(),
                        policeName.getText().toString());
            }else {
                mUploadingPresenter.validateUploading(carNumber.getText().toString(),
                        carColor.getText().toString(),
                        carType,
                        boardColor,
                        mistakeTime.getText().toString(),
                        mistakePlace.getText().toString(),
                        mistakeDescribe.getText().toString(),
                        policeName.getText().toString());
            }
            Log.d(TAG, mistakeTime.getText().toString());
        }
    }

    class Threads extends Thread{
        @Override
        public void run() {
            try {
                while (true){
                    @SuppressLint("SimpleDateFormat")
                    SimpleDateFormat simpleDateFormat =
                            new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");// HH:mm:ss
                    handler.sendMessage(handler.obtainMessage(100,
                            simpleDateFormat.format(new Date())));
                    Thread.sleep(1000);
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
