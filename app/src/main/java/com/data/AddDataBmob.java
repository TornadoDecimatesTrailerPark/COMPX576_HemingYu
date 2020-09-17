package com.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.apple.initbmob.InitBmob;
import com.bean.Comment;
import com.bean.Hot;
import com.bean.MyUser;
import com.bean.Note;
import com.bean.Style;
import com.collecter.ErrorCollecter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.AsyncCustomEndpoints;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.CloudCodeListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadBatchListener;


public class AddDataBmob {
    private static MyUser user = BmobUser.getCurrentUser(MyUser.class);

    //添加一个笔记
    public static void addDataToNote(final String title, final String content, final List<String> image, final List<String> styles) {
        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        note.setAuthor(user);
        note.setUp(0);

        String[] imglist = new String[image.size()];
        final List<BmobFile> imageList = new ArrayList<>();

        if (styles.size()!=0){
            for (String s:styles ) {
                String style = SelectDataBmob.getStyleId(s);
                note.setObjectId(style);
            }
        }
        for (int i = 0;i < image.size();i++) {
            String tempPath = Environment.getExternalStorageDirectory().getPath()
                    + "/XHS/temp/" + System.currentTimeMillis() + ".jpg";
            imglist[i] = tempPath;
        }
        note.setImage(imageList);
        note.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if (e == null) {

                    Log.i("bmob", "save successfully");
                } else {
                    Log.i("bmob", "fail：" + e.getMessage());
                }
            }
        });
    }
    }

