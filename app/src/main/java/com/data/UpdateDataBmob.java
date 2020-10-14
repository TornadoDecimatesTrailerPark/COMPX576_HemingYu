package com.data;


import android.util.Log;
import android.widget.Toast;

import com.apple.initbmob.InitBmob;
import com.bean.MyUser;
import com.bean.Note;
import com.collecter.ErrorCollecter;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;



public class UpdateDataBmob {

    //更新头像
    public static void UpdataHead(final BmobFile bmobFile){
        final MyUser user = BmobUser.getCurrentUser(MyUser.class);
        bmobFile.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    MyUser myUser = new MyUser();
                    myUser.setValue("head",bmobFile);
                    myUser.update(user.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                Toast.makeText(InitBmob.getContext(),"Updated", Toast.LENGTH_SHORT).show();
                                Log.i("bmob","Updated");
                            }else{
                                Toast.makeText(InitBmob.getContext(), ErrorCollecter.errorCode(e), Toast.LENGTH_SHORT).show();
                                Log.i("bmob","Updated failure："+e.getMessage()+","+e.getErrorCode());
                            }
                        }
                    });
                }else{
                    Toast.makeText(InitBmob.getContext(), ErrorCollecter.errorCode(e), Toast.LENGTH_SHORT).show();
                    Log.i("bmob","Upload failure：" + e.getMessage() + e.getErrorCode());
                }
            }
        });
    }

    //更改昵称
    public static void UpdataNickname(String name){
        final MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
        MyUser user = new MyUser();
        user.setValue("nickname",name);
        user.update(myUser.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Toast.makeText(InitBmob.getContext(), "Updated", Toast.LENGTH_SHORT).show();
                    Log.i("bmob","Updated");
                }else{
                    Toast.makeText(InitBmob.getContext(), ErrorCollecter.errorCode(e), Toast.LENGTH_SHORT).show();
                    Log.i("bmob","Updated failure："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

    //初始化ID
    public static void UpdataIDNew(String id){
        final MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
        MyUser user = new MyUser();
        user.setValue("CopyId",id);
        user.setValue("Change",false);
        user.update(myUser.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Toast.makeText(InitBmob.getContext(), "Updated", Toast.LENGTH_SHORT).show();
                    Log.i("bmob","Updated");
                }else{
                    Toast.makeText(InitBmob.getContext(), ErrorCollecter.errorCode(e), Toast.LENGTH_SHORT).show();
                    Log.i("bmob","Updated failure："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

    //更改ID
    public static void UpdataID(String id){
        final MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
        MyUser user = new MyUser();
        user.setValue("CopyId",id);
        user.setValue("Change",true);
        user.update(myUser.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Toast.makeText(InitBmob.getContext(), "Updated", Toast.LENGTH_SHORT).show();
                    Log.i("bmob","Updated");
                }else{
                    Toast.makeText(InitBmob.getContext(), ErrorCollecter.errorCode(e), Toast.LENGTH_SHORT).show();
                    Log.i("bmob","Updated failure："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

    //更改性别
    public static void UpdataSex(boolean sex){
        final MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
        MyUser user = new MyUser();
        user.setValue("sex",sex);
        user.update(myUser.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Toast.makeText(InitBmob.getContext(), "Updated", Toast.LENGTH_SHORT).show();
                    Log.i("bmob","Updated");
                }else{
                    Toast.makeText(InitBmob.getContext(), ErrorCollecter.errorCode(e), Toast.LENGTH_SHORT).show();
                    Log.i("bmob","Updated failure："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

    //更改生日
    public static void UpdataBirthday(String birth){
        final MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
        MyUser user = new MyUser();
        user.setValue("birthday",birth);
        user.update(myUser.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Toast.makeText(InitBmob.getContext(), "Updated", Toast.LENGTH_SHORT).show();
                    Log.i("bmob","Updated");
                }else{
                    Toast.makeText(InitBmob.getContext(), ErrorCollecter.errorCode(e), Toast.LENGTH_SHORT).show();
                    Log.i("bmob","Updated failure："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

    //更改常住地
    public static void UpdataArea(String area){
        final MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
        MyUser user = new MyUser();
        user.setAddress(area);
        user.update(myUser.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Toast.makeText(InitBmob.getContext(), "Updated", Toast.LENGTH_SHORT).show();
                    Log.i("bmob","Updated");
                }else{
                    Toast.makeText(InitBmob.getContext(), ErrorCollecter.errorCode(e), Toast.LENGTH_SHORT).show();
                    Log.i("bmob","Updated failure："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

    //更改个性签名
    public static void UpdataSignature(String signature){
        final MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
        MyUser user = new MyUser();
        user.setValue("signature",signature);
        user.update(myUser.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Toast.makeText(InitBmob.getContext(), "Updated", Toast.LENGTH_SHORT).show();
                    Log.i("bmob","Updated");
                }else{
                    Toast.makeText(InitBmob.getContext(), ErrorCollecter.errorCode(e), Toast.LENGTH_SHORT).show();
                    Log.i("bmob","Updated failure："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }

    //点赞
    public static void clickUp(final Note note){
        note.increment("up",1);
        note.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e==null){
                    Log.i("bmob","Note<" + note.getTitle() + ">Like +1，Total：" + note.getUp());
                }else {
                    Toast.makeText(InitBmob.getContext(), ErrorCollecter.errorCode(e), Toast.LENGTH_SHORT).show();
                    Log.i("bmob","Note<" + note.getTitle() + ">Failure，Total：" + note.getUp());
                }
            }
        });
    }

    //取消点赞
    public static void delUp(final Note note){
        note.increment("up",-1);
        note.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e==null){
                    Log.i("bmob","Note<" + note.getTitle() + ">Like -1，Total：" + note.getUp());
                }else {
                    Toast.makeText(InitBmob.getContext(), ErrorCollecter.errorCode(e), Toast.LENGTH_SHORT).show();
                    Log.i("bmob","Note<" + note.getTitle() + ">Failure，Total：" + note.getUp());
                }
            }
        });
    }

    //密码修改
    public static void updatePwd(String old,String pwd){
        MyUser user = BmobUser.getCurrentUser(MyUser.class);
        user.updateCurrentUserPassword(old, pwd, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e==null){
                    Toast.makeText(InitBmob.getContext(), "Updated", Toast.LENGTH_SHORT).show();
                    Log.i("bmob","Updated");
                }else {
                    Toast.makeText(InitBmob.getContext(), ErrorCollecter.errorCode(e), Toast.LENGTH_SHORT).show();
                    Log.i("bmob","Updated failure：" + e.getMessage() + e.getErrorCode());
                }
            }
        });
    }
}
