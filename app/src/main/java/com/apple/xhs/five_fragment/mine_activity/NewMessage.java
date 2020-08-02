package com.apple.xhs.five_fragment.mine_activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.apple.xhs.R;
import com.apple.xhs.custom_view.InfoSettingTitle;
import com.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by limeng on 2017/8/8.
 */

public class NewMessage extends BaseActivity {
    @BindView(R.id.newmessagrtoolbar)
    InfoSettingTitle toolbar;
    @Override
    public int getContentViewId() {
        return R.layout.newmessage;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar.setImgListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
