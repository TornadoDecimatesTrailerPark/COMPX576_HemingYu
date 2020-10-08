package com.apple.initbmob;

import android.app.Application;
import android.content.Context;
import com.bean.Note;

import java.util.List;

import cn.bmob.v3.Bmob;




public class InitBmob extends Application {
    private static Context context;
    private static List<Note> list;
    private static int guanzhu = 0; //followed
    private static int fans = 0;//follower
    private static int fabu = 0;//post
    private static int shoucang = 0;// like

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
//        SDKInitializer.initialize(getApplicationContext());
        Bmob.initialize(getApplicationContext(),"7909b406e0c17b975ca69afa3a2a23da");
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
