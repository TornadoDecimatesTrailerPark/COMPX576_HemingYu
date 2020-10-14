package com.apple.xhs.custom_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apple.xhs.R;

import me.xiaopan.sketch.SketchImageView;


public class SelfNoteCard extends LinearLayout {
    TextView selfNoteTitle;
    TextView selfNoteShoucang;
    TextView selfNoteDate;

    public SelfNoteCard(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.note_card_custom,this);
        selfNoteTitle = findViewById(R.id.yourselfnotetitle);
        selfNoteShoucang = findViewById(R.id.yourselfnoteshoucang);
        selfNoteDate = findViewById(R.id.yourselfnotedate);

    }

    public void setSelfNoteTitle(String title) {
        selfNoteTitle.setText(title);
    }

    public void setSelfNoteShoucang(String sc) {
        selfNoteShoucang.setText(sc);
    }

    public void setSelfNoteDate(String date) {
        selfNoteDate.setText(date);
    }

}
