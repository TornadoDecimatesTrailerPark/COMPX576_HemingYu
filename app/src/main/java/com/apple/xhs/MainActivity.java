package com.apple.xhs;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.apple.xhs.custom_view.HomeCamera;
import com.apple.xhs.custom_view.MineInfo;
import com.collecter.ActivityCollecter;
import com.base.BaseActivity;

import butterknife.BindView;


public class MainActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tag_main_page)
    TextView tag_main_page;
    @BindView(R.id.tag_personal_info)
    TextView tag_personal_info;
    @BindView(R.id.tag_new_notes)
    ImageView tag_new_notes;
    @BindView(R.id.home_open_camera)
    ImageView home_open_camera;
    @BindView(R.id.home_viewpager)
    View home_viewpager;
    @BindView(R.id.home_personal_info)
    View home_personal_info;

    long exitTime;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
        tag_new_notes.setOnClickListener(this);
        home_open_camera.setOnClickListener(this);
        home_viewpager.setVisibility(View.VISIBLE);

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View view) {
        resetTabColor();
        resetFragment();
        switch (view.getId()) {
            case R.id.tag_main_page:
                home_viewpager.setVisibility(View.VISIBLE);
                tag_main_page.setSelected(true);
                break;
            case R.id.tag_personal_info:
                Intent tag_personal_info = new Intent(MainActivity.this, MineInfo.class);
                startActivity(tag_personal_info);
                break;
            case R.id.tag_new_notes:
            case R.id.home_open_camera:
                Intent tag_new_notes = new Intent(MainActivity.this, HomeCamera.class);
                startActivity(tag_new_notes);
                break;
        }
    }

    @SuppressLint("ResourceAsColor")
    public void resetTabColor() {
        tag_main_page.setSelected(false);
        tag_personal_info.setSelected(false);

    }

    public void resetFragment() {
        home_viewpager.setVisibility(View.INVISIBLE);
        home_personal_info.setVisibility(View.INVISIBLE);

    }


    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            Toast.makeText(this, "Click once to quit", Toast.LENGTH_SHORT).show();
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
