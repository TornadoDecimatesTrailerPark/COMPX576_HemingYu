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
    @BindView(R.id.tag_main_page) TextView tag_main_page;
    @BindView(R.id.tag_personal_info) TextView tag_personal_info;


    @BindView(R.id.home_viewpager) View home_viewpager;
    @BindView(R.id.home_personal_info) View home_personal_info;


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
        tag_main_page.setSelected(true);

        tag_main_page.setOnClickListener(this);
        tag_personal_info.setOnClickListener(this);

        home_viewpager.setVisibility(View.VISIBLE);

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View view) {
        resetTabColor();
        resetFragment();
        switch (view.getId()){
            case R.id.tag_main_page:
                home_viewpager.setVisibility(View.VISIBLE);
                tag_main_page.setSelected(true);
                break;
            case R.id.tag_personal_info:
                home_personal_info.setVisibility(View.VISIBLE);
                tag_personal_info.setSelected(true);
                break;
        }
    }
    @SuppressLint("ResourceAsColor")
    public void resetTabColor(){
        tag_main_page.setSelected(false);
        tag_personal_info.setSelected(false);

    }
    public void resetFragment(){
        home_viewpager.setVisibility(View.INVISIBLE);
        home_personal_info.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onBackPressed() {
        if(System.currentTimeMillis() - exitTime > 2000){
            Toast.makeText(this,"再按一次返回键退出",Toast.LENGTH_SHORT).show();
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
