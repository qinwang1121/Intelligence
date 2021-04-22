package com.qinwang.intelligence.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qinwang.intelligence.R;
import com.qinwang.intelligence.tools.bean.Congestion_sections;

import java.util.List;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/4/22
 * @Description:com.qinwang.intelligence.tools
 * @Version:1.0
 * @function:
 */
public class BannerListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Congestion_sections> mCongestionSectionsList;

    public BannerListAdapter(Context mContext,
                             List<Congestion_sections> mCongestionSectionsList){
        this.mContext = mContext;
        this.mCongestionSectionsList = mCongestionSectionsList;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mCongestionSectionsList.size();
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
        Congestion_sections mCongestionSections = mCongestionSectionsList.get(i);
        BannerListViewHolder.ViewHolder mViewHolder = null;
        if (view == null){
            mViewHolder = new BannerListViewHolder.ViewHolder();
            view = mInflater.inflate(R.layout.list_banner_view, null);
            mViewHolder.roadEva = (TextView) view.findViewById(R.id.banner_roadEva);
            mViewHolder.roadSpeed = (TextView) view.findViewById(R.id.banner_roadSpeed);
            mViewHolder.roadDis = (TextView) view.findViewById(R.id.banner_roadDis);
            mViewHolder.roadTrend = (TextView) view.findViewById(R.id.banner_roadTrend);
            mViewHolder.roadDes = (TextView) view.findViewById(R.id.banner_roadDes);
            view.setTag(mViewHolder);
        }else {
            mViewHolder = (BannerListViewHolder.ViewHolder) view.getTag();
        }
        mViewHolder.roadEva.setText(mCongestionSections.getStatus());
        mViewHolder.roadSpeed.setText(String.valueOf(mCongestionSections.getSpeed()));
        mViewHolder.roadDis.setText(String.valueOf(mCongestionSections.getCongestion_distance()));
        mViewHolder.roadTrend.setText(mCongestionSections.getCongestion_trend());
        mViewHolder.roadDes.setText(mCongestionSections.getSection_desc());
        return view;
    }
}
