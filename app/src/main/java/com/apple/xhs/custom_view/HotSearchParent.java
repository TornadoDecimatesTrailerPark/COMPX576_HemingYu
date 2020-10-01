package com.apple.xhs.custom_view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.apple.xhs.R;



public class HotSearchParent extends LinearLayout {
    public HotSearchParent(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.hotsearchparent,this);
    }
}
