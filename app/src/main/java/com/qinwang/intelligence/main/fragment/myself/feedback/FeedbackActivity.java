package com.qinwang.intelligence.main.fragment.myself.feedback;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mylhyl.circledialog.CircleDialog;
import com.qinwang.base.BaseActivity;
import com.qinwang.intelligence.R;

public class FeedbackActivity extends BaseActivity implements FeedbackContract.FeedbackView, View.OnClickListener {

    private static final int MAX_NUM = 200;
    private TextView feedback_size, toolbar_title;
    private EditText feedback_text, feedback_email;

    private FeedbackContract.FeedbackPresenter mFeedbackPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        initView();
        mFeedbackPresenter = new FeedbackPresenterImpl(FeedbackActivity.this,
                this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFeedbackPresenter.onDestroy();
    }

    public void initView(){
        feedback_text = (EditText) findViewById(R.id.feedback_text_feedback);
        feedback_email = (EditText)findViewById(R.id.feedback_text_Email);
        feedback_size = (TextView)findViewById(R.id.feedback_text_size);
        toolbar_title = (TextView)findViewById(R.id.toolber_titlle);

        toolbar_title.setText(getString(R.string.myself_feedback));
        toolbar_title.setGravity(Gravity.CENTER);

        findViewById(R.id.feedback_button).setOnClickListener(this);

        toolbar_title.setText(getString(R.string.myself_feedback));
        toolbar_title.setGravity(Gravity.CENTER);

        showTextSize(0);
        feedback_text.addTextChangedListener(textWatcher);
    }

    @Override
    public void showDescribeError() {
        feedback_text.setError(getString(R.string.feedback_describe_error));
    }

    @Override
    public void showHintDialog(String msg, View.OnClickListener listener) {
        new CircleDialog.Builder(FeedbackActivity.this)
                .setTitle(getString(R.string.Dialog_title))
                .setText(msg)
                .setPositive(getString(R.string.Dialog_true), listener)
                .show();
    }

    @Override
    public void showEmailError(String msg) {
        feedback_email.setError(msg);
    }

    @Override
    public void closeActivity() {
        finish();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.feedback_button) {
            mFeedbackPresenter.validateFeedback(FeedbackActivity.this,
                    feedback_email.getText().toString(),
                    feedback_text.getText().toString());
        }
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //只要编辑框内容有变化就会调用该方法，charSequence为编辑框变化后的内容
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //编辑框内容变化之前会调用该方法，charSequence为编辑框内容变化之前的内容
        }

        @Override
        public void afterTextChanged(Editable editable) {
            //编辑框内容变化之后会调用该方法，s为编辑框内容变化后的内容
            if (editable.length() > MAX_NUM){
                editable.delete(MAX_NUM, editable.length());
                showHintDialog(getString(R.string.Dialog_feedback_number_error),
                        null);
            }
            showTextSize(editable.length());
        }
    };

    @SuppressLint("SetTextI18n")
    public void showTextSize(int num) {
        feedback_size.setText(getString(R.string.feedback_number) + num);
    }
}