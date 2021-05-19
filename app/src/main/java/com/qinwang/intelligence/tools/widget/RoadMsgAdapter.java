package com.qinwang.intelligence.tools.widget;

import android.view.View;

import com.qinwang.intelligence.R;
import com.qinwang.intelligence.tools.BannerListViewHolder;
import com.qinwang.intelligence.tools.bean.road_traffic;
import com.zhpan.bannerview.BaseBannerAdapter;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/4/22
 * @Description:com.qinwang.intelligence.tools.widget
 * @Version:1.0
 * @function:
 */
public class RoadMsgAdapter extends BaseBannerAdapter<road_traffic, BannerListViewHolder> {
    @Override
    protected void onBind(BannerListViewHolder holder, road_traffic data, int position, int pageSize) {
        holder.bindData(data, position, pageSize);
    }

    @Override
    public BannerListViewHolder createViewHolder(View itemView, int viewType) {
        return new BannerListViewHolder(itemView);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.include_banner_list_view;
    }
}
