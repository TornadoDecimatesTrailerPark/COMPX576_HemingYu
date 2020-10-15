package com.apple.xhs;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bean.MyUser;
import com.base.BaseActivity;
import com.collecter.ErrorCollecter;

import butterknife.BindView;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


public class Login extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_pass)
    TextView userPass;
    @BindView(R.id.go_logon)
    TextView logon;
    @BindView(R.id.go_reset)
    TextView reset;
    @BindView(R.id.bt_login)
    TextView login;

    String name, pass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    public int getContentViewId() {
        return R.layout.login;
    }

    private void initView() {
        login.setOnClickListener(this);
        logon.setOnClickListener(this);
        reset.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                name = userName.getText().toString().trim();
                pass = userPass.getText().toString().trim();
                if (name.equals("") || pass.equals("")) {
                    Toast.makeText(getApplicationContext(), "Account or password can not be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                final MyUser user = new MyUser();
                user.setUsername(name);
                user.setPassword(pass);
                user.login(new SaveListener<MyUser>() {
                    @Override
                    public void done(MyUser myUser, BmobException e) {
                        if (e == null) {
                            login.setText("Loging");
                            Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login.this, MainActivity.class));
                            finish();
                        } else {
                            login.setText("Relog");
                            Toast.makeText(getApplicationContext(), ErrorCollecter.errorCode(e), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            case R.id.go_logon:
                startActivity(new Intent(Login.this, Logon.class));
                break;
            case R.id.go_reset:
                startActivity(new Intent(Login.this, ForgetPass.class));
                break;
        }
    }
}
