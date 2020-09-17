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

public class NoteShow extends BaseActivity implements View.OnClickListener {



    @Override
    public int getContentViewId() {
        return R.layout.note_scan;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @SuppressLint("ResourceAsColor")
    private void initView() {




    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_close_camera:
                Intent home_close_camera = new Intent(NoteShow.this, MainActivity.class);
                startActivity(home_close_camera);
                break;
        }
    }
}

