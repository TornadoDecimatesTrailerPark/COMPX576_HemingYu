package com.apple.xhs.custom_view;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.apple.xhs.R;
import com.base.BaseActivity;

import butterknife.BindView;
import me.panpf.sketch.SketchImageView;
import me.panpf.sketch.zoom.ImageZoomer;


public class NoteEditShowBigPic extends BaseActivity {
    @BindView(R.id.show_big_pic)
    SketchImageView imageView;
    @Override
    public int getContentViewId() {
        return R.layout.note_edit_showbigpic;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        imageView.setZoomEnabled(true);
        ImageZoomer imageZoomer = new ImageZoomer(imageView);
        imageZoomer.zoom(3f,true);
        imageView.displayImage(getIntent().getStringExtra("showbigpic"));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.showbigpic_out, R.anim.showbigpic_out_big);
            }
        });
    }
}
