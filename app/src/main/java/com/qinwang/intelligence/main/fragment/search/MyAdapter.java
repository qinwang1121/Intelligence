package com.qinwang.intelligence.main.fragment.search;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qinwang.intelligence.R;
import com.qinwang.intelligence.tools.bean.Mistake;

import java.util.List;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/3/30
 * @Description:com.qinwang.intelligence.main.fragment.search
 * @Version:1.0
 * @function:
 */
public class MyAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Mistake> mMistakeList;

    public MyAdapter(Context mContext, List<Mistake> mMistakeList){
        this.mContext = mContext;
        this.mMistakeList = mMistakeList;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mMistakeList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Mistake mMistake = mMistakeList.get(i);
        ViewHolder mViewHolder = null;
        if (view == null){
            mViewHolder = new ViewHolder();
            view = mInflater.inflate(R.layout.list_view, null);
            mViewHolder.msgTime = (TextView)view.findViewById(R.id.msgTime);
            mViewHolder.msgPlace = (TextView)view.findViewById(R.id.msgPlace);
            mViewHolder.msgText = (TextView)view.findViewById(R.id.msgText);
            view.setTag(mViewHolder);
        }else {
            mViewHolder = (ViewHolder)view.getTag();
        }
        mViewHolder.msgTime.setText(mMistake.getMistakeTime());
        mViewHolder.msgPlace.setText(mMistake.getMistakePlace());
        mViewHolder.msgText.setText(mMistake.getMistakeDescribe());
        return view;
    }
}
