package com.apple.xhs;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.base.BaseActivity;
import com.collecter.ActivityCollecter;
import butterknife.BindView;


public class MainActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tab_home)
    TextView tab_home;
    @BindView(R.id.tab_search)
    TextView tab_search;
    @BindView(R.id.tab_msg)
    TextView tab_msg;
    @BindView(R.id.tab_me)
    TextView tab_me;

    @BindView(R.id.fragment_home)
    View fragment_home;
    @BindView(R.id.fragment_search)
    View fragment_search;
    @BindView(R.id.fragment_msg)
    View fragment_msg;
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
        tab_search.setOnClickListener(this);
        tab_msg.setOnClickListener(this);
        tab_me.setOnClickListener(this);

        fragment_home.setVisibility(View.VISIBLE);

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View view) {
        resetTabColor();
        resetFragment();
        switch (view.getId()){
            case R.id.tab_home:
                fragment_home.setVisibility(View.VISIBLE);
                tab_home.setSelected(true);
                break;
            case R.id.tab_search:
                fragment_search.setVisibility(View.VISIBLE);
                tab_search.setSelected(true);
                break;
            case R.id.tab_msg:
                fragment_msg.setVisibility(View.VISIBLE);
                tab_msg.setSelected(true);
                break;
            case R.id.tab_me:
                fragment_me.setVisibility(View.VISIBLE);
                tab_me.setSelected(true);
                break;
        }
    }
    @SuppressLint("ResourceAsColor")
    public void resetTabColor(){
        tab_home.setSelected(false);
        tab_search.setSelected(false);
        tab_msg.setSelected(false);
        tab_me.setSelected(false);
    }
    public void resetFragment(){
        fragment_home.setVisibility(View.INVISIBLE);
        fragment_search.setVisibility(View.INVISIBLE);
        fragment_msg.setVisibility(View.INVISIBLE);
        fragment_me.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
        if(System.currentTimeMillis() - exitTime > 2000){
            Toast.makeText(this,"quit", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        }else {
            ActivityCollecter.finishAll();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
