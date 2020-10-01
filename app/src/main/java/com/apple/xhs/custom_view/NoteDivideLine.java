package com.apple.xhs.custom_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.apple.xhs.R;



public class NoteDivideLine extends LinearLayout {
    View view;
    public NoteDivideLine(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.note_pinglun_line,this);
        view = findViewById(R.id.note_pinglunline);
    }
}
