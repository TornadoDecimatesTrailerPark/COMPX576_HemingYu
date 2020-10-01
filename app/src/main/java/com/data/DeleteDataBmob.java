package com.data;

import android.util.Log;
import android.widget.Toast;

import com.apple.initbmob.InitBmob;
import com.bean.MyUser;
import com.bean.Note;
import com.collecter.ErrorCollecter;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.Arrays;

import cn.bmob.v3.AsyncCustomEndpoints;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.CloudCodeListener;
import cn.bmob.v3.listener.UpdateListener;


public class DeleteDataBmob {
    //取消收藏
    public static void deleteLikes(final Note note){
        final MyUser my = BmobUser.getCurrentUser(MyUser.class);
        BmobRelation relation = new BmobRelation();
        relation.remove(note);
        my.setLikes(relation);
        my.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    UpdateDataBmob.delUp(note);

                    InitBmob.setShoucang(InitBmob.getShoucang()-1);

                }else{
                    Toast.makeText(InitBmob.getContext(),ErrorCollecter.errorCode(e),Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    //删除笔记
    public static void deleteNote(final Note note){
        note.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e==null){

                    InitBmob.setFabu(InitBmob.getFabu()-1);

                }else {
                    Toast.makeText(InitBmob.getContext(),ErrorCollecter.errorCode(e),Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    //清空历史搜索
    public static void deleteHistory(){
        MyUser user = BmobUser.getCurrentUser(MyUser.class);
        user.remove("history");
        user.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e==null){

                    Log.i("bmob","清空历史成功");
                }else {
                    Toast.makeText(InitBmob.getContext(),ErrorCollecter.errorCode(e),Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

}
