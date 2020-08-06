package com.apple.xhs;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.base.BaseActivity;
import com.bean.MyUser;

import butterknife.BindView;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;



public class Login extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.user_name) TextView userName;
    @BindView(R.id.user_pass) TextView userPass;
    @BindView(R.id.go_logon) TextView logon;
    @BindView(R.id.bt_login) TextView login;

    String name,pass;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_login:
                name = userName.getText().toString().trim();
                pass = userPass.getText().toString().trim();
                if(name.equals("")||pass.equals("")){
                    Toast.makeText(getApplicationContext(),"account or password should not be empty",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    Intent intent=new Intent(Login.this,MainActivity.class);
                    startActivity(intent);
                }
        }
    }
}
