package com.apple.xhs.custom_view;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.apple.xhs.MainActivity;
import com.apple.xhs.R;
import com.base.BaseActivity;


public class HomeCamera extends BaseActivity implements View.OnClickListener {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_camera);
        initView();
    }

    @Override
    public int getContentViewId() {
        return R.layout.home_camera;
    }

    @SuppressLint("ResourceAsColor")
    private void initView() {

        View note = findViewById(R.id.home_video);
        note.setOnClickListener(this);
        View picture = findViewById(R.id.take_picture);
        picture.setOnClickListener(this);
    }

    public void backToMainpage(View view) {
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_video:
                startActivity(new Intent(HomeCamera.this, NoteEdit.class));
                break;
            case R.id.take_picture:
                dispatchTakePictureIntent();
                break;
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
}
