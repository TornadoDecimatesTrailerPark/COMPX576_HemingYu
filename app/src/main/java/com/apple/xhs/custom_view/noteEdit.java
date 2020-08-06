package com.apple.xhs.custom_view;


import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.apple.xhs.MainActivity;
import com.apple.xhs.R;
import com.base.BaseActivity;
import com.collecter.ActivityCollecter;

import butterknife.BindView;








public class noteEdit extends Activity{

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_edit_view);
    }
}
