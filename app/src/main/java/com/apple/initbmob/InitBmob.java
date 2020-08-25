package com.apple.initbmob;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import com.bean.Note;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;


public class InitBmob extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private static List<Note> list;
    private static int guanzhu = 0;
    private static int fans = 0;
    private static int fabu = 0;
    private static int shoucang = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
      /*  SDKInitializer.initialize(getApplicationContext());*/
       /* Bmob.initialize(getApplicationContext(),"7909b406e0c17b975ca69afa3a2a23da");*/
        BmobConfig config =new BmobConfig.Builder(getApplicationContext())
                .setApplicationId("7909b406e0c17b975ca69afa3a2a23da")//设置appkey
                .setConnectTimeout(30)//请求超时时间（单位为秒）：默认15s
                .setUploadBlockSize(1024*1024)//文件分片上传时每片的大小（单位字节），默认512*1024
                .setFileExpiration(2500)//文件的过期时间(单位为秒)：默认1800s
                .build();
        Bmob.initialize(config);
    }

    public static Context getContext(){
        return context;
    }

    public static List<Note> getList() {
        return list;
    }

    public static void setList(List<Note> list) {
        InitBmob.list = list;
    }

    public static int getGuanzhu() {
        return guanzhu;
    }

    public static void setGuanzhu(int guanzhu) {
        InitBmob.guanzhu = guanzhu;
    }

    public static int getFans() {
        return fans;
    }

    public static void setFans(int fans) {
        InitBmob.fans = fans;
    }

    public static int getFabu() {
        return fabu;
    }

    public static void setFabu(int fabu) {
        InitBmob.fabu = fabu;
    }

    public static int getShoucang() {
        return shoucang;
    }

    public static void setShoucang(int shoucang) {
        InitBmob.shoucang = shoucang;
    }
}
