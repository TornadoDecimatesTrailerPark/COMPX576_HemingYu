package com.apple.xhs.custom_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.compx576_heming_yu.R;

public class InfoSettingTitle extends RelativeLayout {
    View parent,img;
    TextView textView,textViewDone;

    public InfoSettingTitle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initUiView(context,attrs);
    }

    @SuppressLint("NewApi")
    private void initUiView(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.InfoSettingTitle);

    }

    public void setTitleText(String str){
        textView.setText(str);
    }
    public void setImgListener(OnClickListener listener) {
        img.setOnClickListener(listener);
    }
    public void setDoneListener(OnClickListener listener) {
        textViewDone.setOnClickListener(listener);
    }

    public TextView getTextView() {
        return textView;
    }
}
