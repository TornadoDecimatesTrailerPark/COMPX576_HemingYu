package com.apple.xhs;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.collecter.ActivityCollecter;
import com.base.BaseActivity;

import butterknife.BindView;


public class MainActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tab_home)
    TextView tab_home;
    @BindView(R.id.tab_me)
    TextView tab_me;
    @BindView(R.id.fragment_home)
    View fragment_home;
    @BindView(R.id.fragment_me)
    View fragment_me;

    long exitTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        selectAllNote();
        ActivityCollecter.finishOthers();
        initView();
    }

    @Override
    public int getContentViewId() {
        return R.layout.main_activity;
    }

    @SuppressLint("ResourceAsColor")
    private void initView() {
        resetFragment();
        tab_home.setSelected(true);

        tab_home.setOnClickListener(this);

        tab_me.setOnClickListener(this);

        fragment_home.setVisibility(View.VISIBLE);

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View view) {
        resetTabColor();
        resetFragment();
        switch (view.getId()) {
            case R.id.tab_home:
                fragment_home.setVisibility(View.VISIBLE);
                tab_home.setSelected(true);
                break;
            case R.id.tab_me:
                fragment_me.setVisibility(View.VISIBLE);
                tab_me.setSelected(true);
                break;
        }
    }

    @SuppressLint("ResourceAsColor")
    public void resetTabColor() {
        tab_home.setSelected(false);
        tab_me.setSelected(false);
    }

    public void resetFragment() {
        fragment_home.setVisibility(View.INVISIBLE);
        fragment_me.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            Toast.makeText(this, "Click twice to quit", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            ActivityCollecter.finishAll();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
