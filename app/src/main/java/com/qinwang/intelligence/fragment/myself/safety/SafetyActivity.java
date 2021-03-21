package com.qinwang.intelligence.fragment.myself.safety;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

import com.qinwang.base.BaseActivity;
import com.qinwang.intelligence.R;

public class SafetyActivity extends BaseActivity {

    TextView toolbar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety);

        initView();
    }

    public void initView(){
        toolbar_title = (TextView)findViewById(R.id.toolber_titlle);

        toolbar_title.setText(getString(R.string.safetyTitle));
        toolbar_title.setGravity(Gravity.CENTER);
    }
}