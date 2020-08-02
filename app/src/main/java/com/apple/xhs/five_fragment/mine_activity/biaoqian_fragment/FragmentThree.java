package com.apple.xhs.five_fragment.mine_activity.biaoqian_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.apple.xhs.R;

/**
 * Created by limeng on 2017/7/26.
 */

public class FragmentThree extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.guanzhu_biaoqian,container,false);
        return view;
    }
}
