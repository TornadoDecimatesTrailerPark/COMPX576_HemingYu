package com.apple.xhs.five_fragment.mine_activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.apple.xhs.R;
import com.apple.xhs.custom_view.InfoSettingTitle;
import com.base.BaseActivity;
import com.collecter.ErrorCollecter;

import butterknife.BindView;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;


public class ChangePassword extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.changepwdtoolbar)
    InfoSettingTitle toolbar;
    @BindView(R.id.changeoldpwd)
    EditText changeoldpwd;
    @BindView(R.id.changenewpwd)
    EditText changenewpwd;
    @BindView(R.id.changepwdagain)
    EditText changepwdagain;
    @BindView(R.id.surechangepwd)
    Button surechangepwd;

    @Override
    public int getContentViewId() {
        return R.layout.changepassword;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListener();
    }

    private void setListener() {
        toolbar.setImgListener(this);
        surechangepwd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_setting_back:
                finish();
                break;
            case R.id.surechangepwd:
                changePwd();
                finish();
                break;
        }
    }

    private void changePwd() {
        String oldP = changeoldpwd.getText().toString();
        String newP = changenewpwd.getText().toString();
        String newPA = changepwdagain.getText().toString();
        if (!newP.equals(newPA)) {
            Toast.makeText(this, "Please input same password", Toast.LENGTH_SHORT).show();
            return;
        }
        BmobUser.updateCurrentUserPassword(oldP, newP, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), ErrorCollecter.errorCode(e), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
