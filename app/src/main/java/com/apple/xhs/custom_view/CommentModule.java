package com.apple.xhs.custom_view;

import android.content.Context;

import androidx.annotation.Nullable;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apple.xhs.R;

import me.xiaopan.sketch.SketchImageView;


//This class is used to display the comment area
public class CommentModule extends LinearLayout {
    LinearLayout otheruser;
    SketchImageView headPic;
    TextView userName;
    TextView userContent;
    TextView pushDate;

    public CommentModule(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {

        LayoutInflater.from(context).inflate(R.layout.note_pinglun_selfview, this);
        otheruser = findViewById(R.id.otheruser);
        headPic = findViewById(R.id.otheruserhead);
        userName = findViewById(R.id.otherusername);
        userContent = findViewById(R.id.pingluncontent);
        pushDate = findViewById(R.id.pinglundate);
    }

    public void setOtheruserListener(OnClickListener listener) {
        otheruser.setOnClickListener(listener);
    }

    public void setUserContentListener(OnClickListener listener) {
        userContent.setOnClickListener(listener);
    }

    public LinearLayout getOtheruser() {
        return otheruser;
    }

    public void setOtheruser(LinearLayout otheruser) {
        this.otheruser = otheruser;
    }

    public SketchImageView getHeadPic() {
        return headPic;
    }

    public void setHeadPic(SketchImageView headPic) {
        this.headPic = headPic;
    }

    public TextView getUserName() {
        return userName;
    }

    public void setUserName(TextView userName) {
        this.userName = userName;
    }

    public TextView getUserContent() {
        return userContent;
    }

    public void setUserContent(TextView userContent) {
        this.userContent = userContent;
    }

    public TextView getPushDate() {
        return pushDate;
    }

    public void setPushDate(TextView pushDate) {
        this.pushDate = pushDate;
    }
}
