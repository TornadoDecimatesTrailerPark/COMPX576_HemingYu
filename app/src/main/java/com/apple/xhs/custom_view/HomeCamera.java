package com.apple.xhs.custom_view;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.apple.xhs.MainActivity;
import com.apple.xhs.R;
import com.apple.xhs.note.NoteEditView;
import com.base.BaseActivity;

import butterknife.BindView;

public class HomeCamera extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.home_close_camera)
    ImageView home_close_camera;
    @BindView(R.id.home_video)
    ImageView home_video;
    @BindView(R.id.home_note)ImageView home_note;

    @Override
    public int getContentViewId() {
        return R.layout.home_camera;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @SuppressLint("ResourceAsColor")
    private void initView() {

        home_close_camera.setOnClickListener(this);
        home_video.setOnClickListener(this);
        home_note.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_close_camera:
                Intent home_close_camera = new Intent(HomeCamera.this, MainActivity.class);
                startActivity(home_close_camera);
                break;
            case R.id.home_video:
            case R.id.home_note:
                Intent home_video = new Intent(HomeCamera.this, NoteEditView.class);
                startActivity(home_video);
                break;
        }
    }
}
