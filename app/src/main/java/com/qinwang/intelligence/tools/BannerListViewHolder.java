package com.qinwang.intelligence.tools;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.qinwang.intelligence.R;
import com.qinwang.intelligence.tools.bean.road_traffic;
import com.zhpan.bannerview.BaseViewHolder;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/4/22
 * @Description:com.qinwang.intelligence.tools
 * @Version:1.0
 * @function:
 */
public class BannerListViewHolder extends BaseViewHolder<road_traffic> {

    public BannerListViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(road_traffic data, int position, int pageSize) {

        BannerListAdapter mBannerListAdapter =
                new BannerListAdapter(itemView.getContext(), data.getCongestion_sections());

        TextView roadName = (TextView) findView(R.id.banner_roadName);
        ListView roadList = (ListView) findView(R.id.banner_list);

        roadName.setText(data.getRoad_name());
        roadList.setAdapter(mBannerListAdapter);
    }
}
