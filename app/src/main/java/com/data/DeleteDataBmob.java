package com.data;

import android.util.Log;
import android.widget.Toast;

import com.apple.initbmob.InitBmob;
import com.bean.MyUser;
import com.bean.Note;
import com.collecter.ErrorCollecter;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;


public class DeleteDataBmob {
    //cancel like
    public static void deleteLikes(final Note note) {
        final MyUser my = BmobUser.getCurrentUser(MyUser.class);
        BmobRelation relation = new BmobRelation();
        relation.remove(note);
        my.setLikes(relation);
        my.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    UpdateDataBmob.delUp(note);

                    InitBmob.setShoucang(InitBmob.getShoucang() - 1);

                } else {
                    Toast.makeText(InitBmob.getContext(), ErrorCollecter.errorCode(e), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    //delete a note
    public static void deleteNote(final Note note) {
        note.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {

                    InitBmob.setFabu(InitBmob.getFabu() - 1);

                } else {
                    Toast.makeText(InitBmob.getContext(), ErrorCollecter.errorCode(e), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    //delete history of search
    public static void deleteHistory() {
        MyUser user = BmobUser.getCurrentUser(MyUser.class);
        user.remove("history");
        user.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {

                    Log.i("bmob", "Emptied successfully");
                } else {
                    Toast.makeText(InitBmob.getContext(), ErrorCollecter.errorCode(e), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

}
