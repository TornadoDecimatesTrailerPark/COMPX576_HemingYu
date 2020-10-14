package com.apple.xhs;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bean.MyUser;
import com.base.BaseActivity;
import com.collecter.ErrorCollecter;
import com.data.UpdateDataBmob;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;



public class Logon extends BaseActivity {
    @BindView(R.id.logon_user_name) TextView userName;
    @BindView(R.id.logon_user_pass) TextView userPass;
    @BindView(R.id.logon_user_pass_aga) TextView userPassAga;
    @BindView(R.id.logon_user_email) TextView userEmail;
    @BindView(R.id.logon_login) TextView logon_login;
    @BindView(R.id.bt_logon) Button logon;

    String name,pass,passAga,email;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    public int getContentViewId() {
        return R.layout.logon;
    }

    private void initView() {

    }
    //注册
    @OnClick(R.id.bt_logon)
    public void logonOnClick() {
        name = userName.getText().toString().trim();
        pass = userPass.getText().toString().trim();
        passAga = userPassAga.getText().toString().trim();
        email = userEmail.getText().toString().trim();
        if (!passAga.equals(pass)){
            userPassAga.setError("Inconsistent passwords");
            return;
        }else if (!name.matches("[a-zA-Z0-9_]{1,12}")){
            userName.setError("The user name can only be composed of A-Z, a-z，0-9, not more than 12 characters.");
            return;
        }else if (!pass.matches("[a-zA-Z0-9]{1,16}")){
            userPass.setError("The password contains illegal characters or is more than 16 characters in length");
            return;
        }else if (!email.matches("[a-zA-Z_0-9]+@(([a-zA-z0-9]-*)+\\.){1,3}[a-zA-z\\-]+")){
            userEmail.setError("Incorrect mailbox format");
            return;
        }
        MyUser user = new MyUser();
        BmobFile bmobFile = new BmobFile("ft021l_sm.png","","http://bmob-cdn-13046.b0.upaiyun.com/2017/08/09/6d5a446c40af5dc88025972632129a03.png");
        user.setUsername(name);
        user.setPassword(pass);
        user.setNickname("User" + System.currentTimeMillis());
        user.setHead(bmobFile);
        if (email != null){
            user.setEmail(email);
        }
        user.signUp(new SaveListener<MyUser>() {
            @Override
            public void done(MyUser myUser, BmobException e) {
                if (e==null){
                    UpdateDataBmob.UpdataIDNew(myUser.getObjectId());
                    startActivity(new Intent(Logon.this,Login.class));
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), ErrorCollecter.errorCode(e),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @OnClick(R.id.logon_login)
    public void loginOnClick(){
        finish();
    }
}
