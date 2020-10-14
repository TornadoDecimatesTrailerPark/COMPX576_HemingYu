package com.apple.xhs.note;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.apple.xhs.R;
import com.apple.xhs.custom_view.InfoSettingTitle;
import com.apple.xhs.custom_view.SelfNoteCard;
import com.base.BaseActivity;
import com.bean.MyUser;
import com.bean.Note;
import com.data.DeleteDataBmob;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;



public class SelfNoteScan extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.selfnotetoolbar)
    InfoSettingTitle toolbar;
    @BindView(R.id.note_card_parent)
    LinearLayout parent;
    MyUser user;
    @Override
    public int getContentViewId() {
        return R.layout.note_selfnote;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        addListener();
    }

    private void addListener() {
        toolbar.setDoneListener(this);
        toolbar.setImgListener(this);
    }

    private void initData() {
        user = (MyUser) getIntent().getSerializableExtra("userselfnote");
        if(user.getObjectId().equals(BmobUser.getCurrentUser(MyUser.class).getObjectId())){
            toolbar.setTitleText("My notes");
        }else {
            toolbar.setTitleText("His notes");
        }
        BmobQuery<Note> query = new BmobQuery<Note>();
        query.addWhereEqualTo("author",user);
        query.include("author");
        query.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);
        query.findObjects(new FindListener<Note>() {
            @Override
            public void done(List<Note> list, BmobException e) {
                if(e==null){
                    Log.i("bmob","get successfully");
                    addNoteCard(list);
                }else{
                    Log.i("bmob","failure"+e.getMessage() + e.getErrorCode());
                }
            }
        });
    }

    private void addNoteCard(final List<Note> list) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for(int i = list.size()-1 ; i >= 0 ; i--){
                    final Note note = list.get(i);
                    SelfNoteCard card = new SelfNoteCard(getApplicationContext());
                    card.setSelfNoteTitle(note.getTitle());
                    card.setSelfNoteShoucang("liked: "+note.getUp()+" times"+"\n"+"\n"+"Press to delete this note");
                    card.setSelfNoteDate(note.getUpdatedAt());
                    if(note.getImage().size()==1){

                    }else if(note.getImage().size()==2){

                    }else if(note.getImage().size()==3){

                    }else if(note.getImage().size()>=4){

                    }
                    card.setTag(i);
                    parent.addView(card);
                    card.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //跳转到该笔记浏览页面
                            Intent intent = new Intent(SelfNoteScan.this,NoteScan.class);
                            intent.putExtra("userdata", (Serializable) note);
                            int k = (int) view.getTag();
                            intent.putExtra("id",k);
                            startActivity(intent);
                        }
                    });
                    card.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View view) {
                            deleteCurrentNote(view,note);
                            return true;
                        }
                    });
                }
            }
        });
    }
    //删除笔记
    private void deleteCurrentNote(final View view, final Note note) {
        if(user.getObjectId().equals(BmobUser.getCurrentUser(MyUser.class).getObjectId())){
            new AlertDialog.Builder(this)
                    .setTitle("Delete")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            parent.removeView(view);
                            DeleteDataBmob.deleteNote(note);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.my_setting_back:
                finish();
                break;
        }
    }
}
