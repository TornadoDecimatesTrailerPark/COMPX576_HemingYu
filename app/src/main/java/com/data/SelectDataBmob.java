package com.data;

import android.util.Log;
import android.widget.Toast;

import com.apple.initbmob.InitBmob;
import com.bean.Comment;
import com.bean.Hot;
import com.bean.MyUser;
import com.bean.Note;
import com.bean.Style;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


public class SelectDataBmob {
    //This is a class that adds, deletes and modifies the data in the application, but most of the functions are not actually installed.
    //Find the notes corresponding to styleName in the Style table
    public static void getNoteByStyle(String styleName) {
        BmobQuery<Note> query = new BmobQuery<Note>();
        Style style = new Style();
        style.setObjectId(getStyleId(styleName));
        query.addWhereRelatedTo("note", new BmobPointer(style));
        query.findObjects(new FindListener<Note>() {
            @Override
            public void done(List<Note> list, BmobException e) {
                if (e == null) {

                } else {
                    Log.i("bmob", e + "");
                }
            }
        });
    }

    //Get my notes
    public static void getMineNote() {
        MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
        BmobQuery<Note> query = new BmobQuery<Note>();
        query.addWhereEqualTo("author", myUser);
        query.findObjects(new FindListener<Note>() {
            @Override
            public void done(List<Note> list, BmobException e) {
                if (e == null) {
                    Log.i("bmob", "Success");
                } else {
                    Log.i("bmob", "Failure：" + e.getMessage() + e.getErrorCode());
                }
            }
        });
    }

    //关注列表
    public void selectAttentions() {
        MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
        BmobQuery<MyUser> query = new BmobQuery<MyUser>();
        query.addWhereRelatedTo("attention", new BmobPointer(myUser));
        query.findObjects(new FindListener<MyUser>() {
            @Override
            public void done(List<MyUser> list, BmobException e) {
                if (e == null) {

                } else {
                    Log.i("bmob", "Failure：" + e.getMessage() + e.getErrorCode());
                }
            }
        });
    }

    //fans list
    public void selectFans() {
        MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
        BmobQuery<MyUser> query = new BmobQuery<MyUser>();
        query.addWhereRelatedTo("fans", new BmobPointer(myUser));
        query.findObjects(new FindListener<MyUser>() {
            @Override
            public void done(List<MyUser> list, BmobException e) {
                if (e == null) {

                } else {
                    Log.i("bmob", "Failure：" + e.getMessage() + e.getErrorCode());
                }
            }
        });
    }

    //my liked
    public void selectLikes() {
        MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
        BmobQuery<Note> query = new BmobQuery<Note>();
        query.addWhereRelatedTo("likes", new BmobPointer(myUser));
        query.findObjects(new FindListener<Note>() {
            @Override
            public void done(List<Note> list, BmobException e) {
                if (e == null) {

                } else {
                    Log.i("bmob", "Failure：" + e.getMessage() + e.getErrorCode());
                }
            }
        });
    }

    //search my notes
    public void selectMore(final String ss) {
        BmobQuery<Note> query = new BmobQuery<Note>();
        query.include("author");
        query.findObjects(new FindListener<Note>() {
            @Override
            public void done(List<Note> list, BmobException e) {
                AddDataBmob.addHistory(ss);
                AddDataBmob.addHot(ss);
                if (e == null) {
                    List<Note> newList = new ArrayList<>();
                    for (Note note : list) {
                        if (note.getTitle().contains(ss)) {
                            newList.add(note);
                        }
                    }
                    Log.i("bmob", "total：" + newList.size());
                    if (newList.size() == 0) {
//                        Toast.makeText(InitBmob.getContext(),"结果不存在",Toast.LENGTH_SHORT).show();
                    } else {
                        //代码块
                    }
                } else {
                    Log.i("bmob", "Failure：" + e.getErrorCode() + e.getMessage());
                }
            }
        });
    }

    //Fuzzy query (user)
    public void selectUser(final String ss) {
        BmobQuery<MyUser> query = new BmobQuery<MyUser>();
        query.findObjects(new FindListener<MyUser>() {
            @Override
            public void done(List<MyUser> list, BmobException e) {
                AddDataBmob.addHistory(ss);
                AddDataBmob.addHot(ss);
                if (e == null) {
                    List<MyUser> newList = new ArrayList<>();
                    for (MyUser user : list) {
                        if (user.getNickname().contains(ss)) {
                            newList.add(user);
                        }
                    }
                    Log.i("bmob", "结果个数：" + newList.size());
                    if (newList.size() == 0) {
//                        Toast.makeText(InitBmob.getContext(),"结果不存在",Toast.LENGTH_SHORT).show();
                    } else {
                        //代码块
                    }
                } else {
                    Log.i("bmob", "Failure：" + e.getErrorCode() + e.getMessage());
                }
            }
        });
    }

    //Get the top 16 hot searches
    public void selectHot() {
        BmobQuery<Hot> query = new BmobQuery<Hot>();
        query.order("-number");
        query.setLimit(16);
        query.findObjects(new FindListener<Hot>() {
            @Override
            public void done(List<Hot> list, BmobException e) {
                if (e == null) {

                } else {
                    Log.i("bmob", "Failure：" + e.getErrorCode() + e.getMessage());
                }
            }
        });
    }

    //Query comments-first page
    public void selectComment(Note note) {
        BmobQuery<Comment> query = new BmobQuery<Comment>();
        query.addWhereEqualTo("note", note);
        query.order("-createdAt");
        query.include("user");
        query.setLimit(3);
        query.findObjects(new FindListener<Comment>() {
            @Override
            public void done(List<Comment> list, BmobException e) {
                if (e == null) {
                    Toast.makeText(InitBmob.getContext(), "get " + list.size() + " comments", Toast.LENGTH_SHORT).show();
                } else {
                    Log.i("bmob", "Failure：" + e.getErrorCode() + e.getMessage());
                }
            }
        });
    }

    //Check the list of notes of the people you follow.
    public void selectGuanzhu() {
        MyUser user = BmobUser.getCurrentUser(MyUser.class);
        BmobQuery<MyUser> queryUser = new BmobQuery<MyUser>();
        queryUser.addWhereRelatedTo("attention", new BmobPointer(user));
        BmobQuery<Note> queryNote = new BmobQuery<Note>();
        queryNote.addWhereMatchesQuery("author", "MyUser", queryUser);
        queryNote.findObjects(new FindListener<Note>() {
            @Override
            public void done(List<Note> list, BmobException e) {
                if (e == null) {

                } else {

                }
            }
        });
    }

    //Whether the user is being followed
    public void isGuanzhu(MyUser other) {
        MyUser user = BmobUser.getCurrentUser(MyUser.class);
        BmobQuery<MyUser> query1 = new BmobQuery<MyUser>();
        BmobQuery<MyUser> query2 = new BmobQuery<MyUser>();
        query1.addWhereRelatedTo("attention", new BmobPointer(user));

    }

    public static String getStyleId(String name) {
        switch (name) {
            case "Sneaker":
                return "Nbze4449";
            case "Fashion":
                return "HEXG999Q";
            case "Trip":
                return "Rbun1116";
            case "Food":
                return "rAuZFFFs";
            case "Game":
                return "sdQ1777d";
            case "Movie":
                return "hBaF999C";
            case "Music":
                return "byZHwww1";
            case "PC":
                return "cSvsVVVu";
            case "Phone":
                return "jeM5sssz";
            default:
                return null;
        }
    }
}
