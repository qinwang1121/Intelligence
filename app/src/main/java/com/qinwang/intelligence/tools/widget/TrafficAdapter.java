package com.qinwang.intelligence.tools.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.qinwang.intelligence.R;
import com.qinwang.intelligence.main.fragment.home.HomeFragment;
import com.qinwang.intelligence.tools.BannerListAdapter;
import com.qinwang.intelligence.tools.bean.road_traffic;

import java.util.List;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/5/7
 * @Description:com.qinwang.intelligence.tools.widget
 * @Version:1.0
 * @function:
 */
public class TrafficAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<road_traffic> mRoadTraffics;

    public TrafficAdapter(Context mContext, List<road_traffic> mRoadTraffics){
        this.mContext = mContext;
        this.mRoadTraffics = mRoadTraffics;
        mInflater = LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        return mRoadTraffics.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        road_traffic roadTraffic = mRoadTraffics.get(i);
        HomeFragment.ViewHolder mViewHolder = null;
        if (view == null){
            mViewHolder = new HomeFragment.ViewHolder();
            view = mInflater.inflate(R.layout.include_banner_list_view, null);
            mViewHolder.roadName = view.findViewById(R.id.banner_roadName);
            mViewHolder.congestionListView = view.findViewById(R.id.banner_list);
            view.setTag(mViewHolder);
        }else {
            mViewHolder = (HomeFragment.ViewHolder)view.getTag();
        }
        mViewHolder.roadName.setText(roadTraffic.getRoad_name());
        mViewHolder.congestionListView.setAdapter(new BannerListAdapter(mContext, roadTraffic.getCongestion_sections()));
        return view;
    }
}
