package com.qinwang.intelligence.fragment.mistake;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.qinwang.base.BaseFragment;
import com.qinwang.intelligence.R;

public class MistakeFragment extends BaseFragment implements View.OnClickListener {

    private NavController navController;
    int bt_sc = 0, bt_ul = 0;
    private boolean isChange = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mistake, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        navController = Navigation.findNavController(getActivity(),R.id.fragment2);

        getView().findViewById(R.id.button_uploading)
                .setOnClickListener(this);
        getView().findViewById(R.id.button_search)
                .setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_search:
                isChange = true;
                bt_sc ++;
                bt_ul = 0;
                if (bt_sc == 1){
                    navController.navigate(R.id.action_uploadFragment_to_searchFragment);
                }else {

                }
                break;
            case R.id.button_uploading:
                if (isChange){
                    bt_sc = 0;
                    bt_ul ++;
                    if (bt_ul == 1){
                        navController.navigate(R.id.action_searchFragment_to_uploadFragment);
                    }else {

                    }
                } else {

                }
                break;
        }
    }
}
