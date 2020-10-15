package com.apple.xhs.note;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.apple.util.AppBarStateChangeListener;
import com.apple.util.MySketchViewPagerAdapter;
import com.apple.xhs.R;
import com.apple.xhs.custom_view.CommentModule;
import com.base.BaseActivity;
import com.bean.Comment;
import com.bean.MyUser;
import com.bean.Note;
import com.data.AddDataBmob;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import me.xiaopan.sketch.SketchImageView;
import me.xiaopan.sketch.process.CircleImageProcessor;
import me.xiaopan.sketch.request.DisplayOptions;


public class NoteScan extends BaseActivity implements View.OnClickListener, View.OnLayoutChangeListener {
    //The telescopic toolbar, jumps to the corresponding note list through different tag, but does not complete this function.
    @BindView(R.id.note_appbar)
    AppBarLayout appBarLayout;
    @BindView(R.id.note_collapsing)
    CollapsingToolbarLayout collapsing;
    @BindView(R.id.note_toolbar)
    Toolbar toolbar;
    @BindView(R.id.playButton)
    ButtonBarLayout playButton;
    //user info
    @BindView(R.id.pic_viewpager)
    ViewPager pic_viewpager;

    @BindView(R.id.username_toolbar)
    TextView usernametoolbar;
    /*@BindView(R.id.userheadimage_context)
    SketchImageView userheadimagecontext;*/
    @BindView(R.id.username_context)
    TextView usernamecontext;
    @BindView(R.id.user_notetitle)
    TextView usernotetitle;
    @BindView(R.id.user_notecontext)
    TextView usernotecontext;
    @BindView(R.id.submitdate)
    TextView submitdate;
    @BindView(R.id.note_beishoucang)
    TextView note_beishoucang;
    @BindView(R.id.note_click_pinglun)
    TextView noteclickpingl;
    @BindView(R.id.note_popedittext)
    LinearLayout popedittext;
    @BindView(R.id.edittext)
    EditText editText;
    @BindView(R.id.note_pinglunparent)
    LinearLayout note_pinglunparent;
    @BindView(R.id.note_fabupinglun)
    Button note_fabupinglun;
    /* @BindView(R.id.current_userhead_img)
     SketchImageView current_userhead_img;*/
    @BindView(R.id.pinglunSum)
    TextView pinglunSum;
    @BindView(R.id.show_morePinglun)
    TextView show_morePinglun;
    @BindView(R.id.note_guanzhu)//follow
            Button guanzhu;
    Note note;
    //Current note writer
    MyUser myUser;
    //Current app user
    MyUser currentUser;
    List<SketchImageView> list = new ArrayList<>();
    int keyHeight;
    DisplayOptions displayOptions;
    List<Comment> allpinglun = new ArrayList<>();
    boolean isFollow = false;

    @Override
    public int getContentViewId() {
        return R.layout.note_scan;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentUser = BmobUser.getCurrentUser(MyUser.class);
        setUserHeadImage();
        setNoteData();
        setCollapsingToolbar();
        setAppbarCollapsing();
        setListener();
    }

    private void setListener() {
        popedittext.addOnLayoutChangeListener(this);
        noteclickpingl.setOnClickListener(this);
        note_fabupinglun.setOnClickListener(this);
        pinglunSum.setOnClickListener(this);
        show_morePinglun.setOnClickListener(this);
        usernametoolbar.setOnClickListener(this);
        /*   userheadimagecontext.setOnClickListener(this);*/
        usernamecontext.setOnClickListener(this);
        guanzhu.setOnClickListener(this);
    }

    private void setUserHeadImage() {
        displayOptions = new DisplayOptions();
        displayOptions.setImageProcessor(CircleImageProcessor.getInstance());
      /*  userheadimagecontext.setOptions(displayOptions);
        current_userhead_img.setOptions(displayOptions);*/
        keyHeight = getWindowManager().getDefaultDisplay().getHeight() / 3;
    }

    private void setNoteData() {
        //获取intent传来的数据，设置页面
        note = (Note) getIntent().getSerializableExtra("userdata");
        myUser = note.getAuthor();
        if (myUser.getObjectId().equals(BmobUser.getCurrentUser(MyUser.class).getObjectId())) {
            guanzhu.setVisibility(View.INVISIBLE);
        }
        Map<String, Integer> map = new HashMap<>();
        map.put("iPhone 12 is available now", R.drawable.note0);
        map.put("The best Air Jordan One", R.drawable.note1);
        map.put("Why people love minecraft", R.drawable.note2);
        map.put("S10 Champion is on", R.drawable.note3);
        map.put("Java is the best language", R.drawable.note4);
        map.put("Compx576 is a good course", R.drawable.note5);

        String title = note.getTitle();
        SketchImageView imageView = new SketchImageView(this);
        if (map.get(title) == null) {
            imageView.displayResourceImage(R.drawable.note5);
        } else {
            imageView.displayResourceImage(map.get(title));
        }

        imageView.getOptions().setDecodeGifImage(true);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        list.add(imageView);

        MySketchViewPagerAdapter adapter = new MySketchViewPagerAdapter(list);
        pic_viewpager.setAdapter(adapter);
        //The picture is read locally, and the annotated statement is to read the picture from the cloud service.
        //sketchimageview.displayImage(note.getImage().get(0).getUrl());
        //The avatar of the comment area is set to the current user
        /* current_userhead_img.displayImage(currentUser.getHead().getUrl());*/
        usernametoolbar.setText(myUser.getNickname());
        /*   userheadimagecontext.displayImage(myUser.getHead().getUrl());*/
        usernamecontext.setText(myUser.getNickname());
        usernotetitle.setText(note.getTitle());
        usernotecontext.setText(note.getContent());
        submitdate.setText(note.getCreatedAt());
        note_beishoucang.setText(note.getUp() + " Likes");
        addThisNotePinglunList(note);
    }

    private void addThisNotePinglunList(Note note) {
        BmobQuery<Comment> query = new BmobQuery<Comment>();
        query.addWhereEqualTo("note", note);
        query.order("-createdAt");
        query.include("user");
        //query.setLimit(3);
        query.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);
        query.findObjects(new FindListener<Comment>() {
            @Override
            public void done(List<Comment> list, BmobException e) {
                if (list == null) {
                    return;
                }
                allpinglun = list;
                pinglunSum.setText(list.size() + " Comments");
                show_morePinglun.setText("See all " + list.size() + " comments");
                if (e == null) {
                    for (int i = 0; i < list.size() && i < 3; i++) {
                        Comment comment = list.get(i);
                        final String url = comment.getUser().getHead().getUrl();
                        final String nickname = comment.getUser().getNickname();
                        final String createdAt = comment.getCreatedAt();
                        final String content = comment.getContent();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                CommentModule module = new CommentModule(getApplicationContext(), null);
                                module.getHeadPic().setOptions(displayOptions);
                                module.getHeadPic().displayImage(url);
                                module.getUserContent().setText(content);
                                module.getUserName().setText(nickname);
                                module.getPushDate().setText(createdAt);
                                note_pinglunparent.addView(module);
                            }
                        });
                    }

                } else {
                    Log.i("bmob", "failure" + e.getErrorCode() + e.getMessage());
                }
            }
        });
    }

    private void setAppbarCollapsing() {
        collapsing.setTitle(" ");
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.EXPANDED) {
                    playButton.setVisibility(View.GONE);
                } else if (state == State.COLLAPSED) {
                    playButton.setVisibility(View.VISIBLE);
                } else {
                    playButton.setVisibility(View.GONE);
                }
            }
        });
    }

    //set toolbar
    private void setCollapsingToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //return
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //Right menu, pop up to share from the bottom, and set the background color to translucent
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.note_click_pinglun:
                //Pop up soft keyboard
                popedittext.setVisibility(View.VISIBLE);
                editText.setFocusable(true);
                editText.setFocusableInTouchMode(true);
                editText.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                break;
            //Click the post button
            case R.id.note_fabupinglun:
                String pingluncontent = editText.getText().toString();
                if (pingluncontent.equals("")) {
                    Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                addPingLunContentToView(pingluncontent);
                break;
            case R.id.pinglunSum:
            case R.id.show_morePinglun:
                if (allpinglun.size() == 0) {
                    Toast.makeText(this, "No comment", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(NoteScan.this, NoteAllPinglun.class);
                intent.putExtra("allpinglun", (Serializable) allpinglun);
                startActivity(intent);
                break;
            /*   case R.id.userheadimage_context:*/
            case R.id.username_context:
            case R.id.username_toolbar:
                Intent notelist = new Intent(NoteScan.this, SelfNoteScan.class);
                notelist.putExtra("userselfnote", myUser);
                startActivity(notelist);
                break;
            case R.id.note_guanzhu:
                guanZhu();
                break;
        }
    }

    // Add user-submitted comments to the interface
    private void addPingLunContentToView(String s) {
        CommentModule layout = new CommentModule(this, null);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dateTime = format.format(new Date());
        layout.getHeadPic().setOptions(displayOptions);
        layout.getHeadPic().displayImage(currentUser.getHead().getUrl());
        layout.getUserName().setText(currentUser.getNickname());
        layout.getUserContent().setText(s);
        layout.getPushDate().setText(dateTime);
        note_pinglunparent.addView(layout, 0);
        AddDataBmob.addComment(note, s);
        editText.setText("");
        popedittext.setVisibility(View.INVISIBLE);
        InputMethodManager imm2 = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm2.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    //Monitor whether the soft keyboard pops up
    @Override
    public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
        if (i7 != 0 && i3 != 0 && (i7 - i3 > keyHeight)) {
            popedittext.setVisibility(View.VISIBLE);
        } else if (i7 != 0 && i3 != 0 && (i3 - i7 > keyHeight)) {
            popedittext.setVisibility(View.GONE);
        }
    }

    //follow this writer
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void guanZhu() {
        if (isFollow == false) {
            guanzhu.setText("Followed");
            guanzhu.setTextColor(getResources().getColor(R.color.gray));
            guanzhu.setBackground(getResources().getDrawable(R.drawable.cancelguanzhu_buttonshape));
            isFollow = true;
        } else {
            guanzhu.setText("+ Follow");
            guanzhu.setTextColor(getResources().getColor(R.color.xhsColor));
            guanzhu.setBackground(getResources().getDrawable(R.drawable.addguanzhu_buttonshape));
            isFollow = false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.note_toolbar_menu, menu);
        return true;
    }
}