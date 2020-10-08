package com.apple.xhs.five_fragment.mine_activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.apple.xhs.Login;
import com.apple.xhs.R;
import com.apple.xhs.note.SelfNoteScan;
import com.bean.MyUser;
import com.collecter.CacheCollecter;

import cn.bmob.v3.BmobUser;
import me.xiaopan.sketch.SketchImageView;
import me.xiaopan.sketch.process.CircleImageProcessor;
import me.xiaopan.sketch.request.DisplayOptions;




public class MeFragment extends Fragment implements View.OnClickListener {

    SketchImageView head_icon;
    TextView nickname;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me_layout,container,false);
        initView(view);
        setViewListener(view);
        return view;
    }

    private void initView(View view) {
        head_icon = view.findViewById(R.id.img_me_user_head);
        nickname = view.findViewById(R.id.me_nickname);
    }

    private void setViewListener(View view) {
        view.findViewById(R.id.me_head).setOnClickListener(this);
        view.findViewById(R.id.mine_exit_account).setOnClickListener(this);
        view.findViewById(R.id.myselfnote).setOnClickListener(this);
        view.findViewById(R.id.ge).setOnClickListener(this);
        view.findViewById(R.id.mypasswordchange).setOnClickListener(this);
        view.findViewById(R.id.clearcache).setOnClickListener(this);

    }

    @Override
    public void onStart() {
        super.onStart();
        MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
        DisplayOptions displayOptions = new DisplayOptions();
        displayOptions.setImageProcessor(CircleImageProcessor.getInstance());
        head_icon.setOptions(displayOptions);
        if(myUser.getHead()!=null){
            head_icon.displayImage(myUser.getHead().getUrl());
        }
        if(myUser.getNickname() != null){
            nickname.setText(myUser.getNickname());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mine_exit_account:
                popExitAialog();
                break;
            case R.id.ge:
                startActivity(new Intent(getActivity(), MineUserInfoSetting.class));
                break;
            case R.id.me_head:
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.addCategory(Intent.CATEGORY_OPENABLE);
                galleryIntent.setType("image/*");//图片
                startActivityForResult(galleryIntent, 1);
                break;
            case R.id.myselfnote:
                Intent selfnote = new Intent(getActivity(), SelfNoteScan.class);
                selfnote.putExtra("userselfnote",BmobUser.getCurrentUser(MyUser.class));
                startActivity(selfnote);
                break;
            case R.id.mypasswordchange:
                startActivity(new Intent(getActivity(),ChangePassword.class));
                break;
            case R.id.clearcache:
                popAlertDialog();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case 0:
                break;
            case 1:
                Uri originalUri=data.getData();
                String []imgs1={MediaStore.Images.Media.DATA};//将图片URI转换成存储路径
                Cursor cursor=getActivity().managedQuery(originalUri, imgs1, null, null, null);
                int index=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                String tempPath = Environment.getExternalStorageDirectory().getPath()
                        + "/XHS/temp/" + System.currentTimeMillis() + ".jpg";

                Log.i("bmob","img address：" + tempPath);

                head_icon.displayImage(tempPath);
                break;
        }
    }

    private void popAlertDialog() {
        new AlertDialog.Builder(getActivity()).setTitle("Clear cache")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getContext(),"Finish clear",Toast.LENGTH_SHORT).show();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                CacheCollecter.clearAllCache(getContext());

                            }
                        }).start();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }

    //退出账户的方法
    private void popExitAialog() {
        new AlertDialog.Builder(getActivity()).setTitle("Log out")
                .setMessage("Log out？")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //确定退出
                        BmobUser.logOut();   //清除缓存用户对象
                        startActivity(new Intent(getActivity(), Login.class));
                        Toast.makeText(getContext(),"Quit",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
    }

}
