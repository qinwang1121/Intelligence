package com.qinwang.intelligence.main.fragment.upload;

import android.annotation.SuppressLint;
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

import com.qinwang.base.BaseFragment;
import com.qinwang.intelligence.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UploadFragment extends BaseFragment {

    private final String TAG = "UploadingFragment";

    private EditText carNumber, carColor, otherType,
            mistakeTime, mistakePlace,
            mistakeDescribe, policeName;
    private LinearLayout layoutOtherType;
    private CheckBox carBig, carSmall, truckBig, truckSmall,
            motorcycle, other,
            colorBlue, colorYellow, colorGreen, colorWhite, colorBlack;
    private TextView spaceType, spaceColor;

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
    }

    public void initView(){

        carNumber = getView().findViewById(R.id.carNumber);
        carColor = getView().findViewById(R.id.carColor);
        otherType = getView().findViewById(R.id.otherType);
        mistakeTime = getView().findViewById(R.id.mistakeTime);
        mistakePlace = getView().findViewById(R.id.mistake_place);
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
