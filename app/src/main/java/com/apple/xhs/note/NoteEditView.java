package com.apple.xhs.note;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apple.xhs.custom_view.InfoSettingTitle;
import com.apple.xhs.R;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.base.BaseActivity;
import com.bumptech.glide.Glide;
import com.data.AddDataBmob;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.yuyh.library.imgsel.ImageLoader;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.xiaopan.sketch.SketchImageView;

//edit notes page
public class NoteEditView extends BaseActivity implements View.OnClickListener, TextWatcher {
    @BindView(R.id.send_note_title)
    InfoSettingTitle noteToolBar;
    @BindView(R.id.note_title)
    EditText noteTitle;
    @BindView(R.id.note_context)
    EditText noteContext;
    @BindView(R.id.note_num_limit)
    TextView limit;
    @BindView(R.id.note_add_pic)
    ImageView noteAddPic;


    @BindView(R.id.note_sneaker)
    CheckBox noteSneaker;
    @BindView(R.id.note_fashion)
    CheckBox noteFashion;
    @BindView(R.id.note_trip)
    CheckBox noteTrip;
    @BindView(R.id.note_food)
    CheckBox noteFood;
    @BindView(R.id.note_game)
    CheckBox noteGame;
    @BindView(R.id.note_movie)
    CheckBox noteMovie;
    @BindView(R.id.note_music)
    CheckBox noteMusic;
    @BindView(R.id.note_pc)
    CheckBox notePc;
    @BindView(R.id.note_phone)
    CheckBox notePhone;

    String title;
    String context;
    List<String> getCheckData = new ArrayList<>();
    List<CheckBox> checkItem = new ArrayList<>();
    String[] strings = {"Sneaker", "Fashion", "Trip", "Food", "Game", "Movie", "Music", "PC", "Phone"};
    LinearLayout linearLayout;
    private List<String> pathList = new ArrayList<>();
    ImgSelConfig config;
    private static final int REQUEST_CODE = 0;

    @Override
    public int getContentViewId() {
        return R.layout.note_edit_view;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        initViewListener();
        initCheckItem();
        initImageSelector();
    }

    private ImageLoader loader = new ImageLoader() {
        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    };

    //ImageSelector framework sets UI and functions
    private void initImageSelector() {
        config = new ImgSelConfig.Builder(this, loader)
                .multiSelect(true)
                // 是否记住上次选中记录
                .rememberSelected(false)
                // 使用沉浸式状态栏
                .statusBarColor(Color.parseColor("#E1282D"))
                .build();
    }


    private void initCheckItem() {
        checkItem.add(noteSneaker);
        checkItem.add(noteFashion);
        checkItem.add(noteTrip);
        checkItem.add(noteFood);
        checkItem.add(noteGame);
        checkItem.add(noteMovie);
        checkItem.add(noteMusic);
        checkItem.add(notePc);
        checkItem.add(notePhone);
    }

    private void initViewListener() {
        //Toolbar on top
        noteToolBar.setImgListener(this);
        noteToolBar.setDoneListener(this);
        //pic in note
        noteTitle.setOnClickListener(this);
        noteContext.setOnClickListener(this);
        noteAddPic.setOnClickListener(this);


        noteTitle.addTextChangedListener(this);
        noteContext.addTextChangedListener(this);
        //Check box
        noteSneaker.setOnClickListener(this);
        noteFashion.setOnClickListener(this);
        noteTrip.setOnClickListener(this);
        noteFood.setOnClickListener(this);
        noteGame.setOnClickListener(this);
        noteMovie.setOnClickListener(this);
        noteMusic.setOnClickListener(this);
        notePc.setOnClickListener(this);
        notePhone.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_setting_back:

                finish();
                break;
            case R.id.my_setting_done:
                title = noteTitle.getText().toString();
                context = noteContext.getText().toString();
                addCheckData();
                //addCheckData();//return data to getCheckData；
                if (pathList.size() == 0) {
                    Toast.makeText(this, "Please add at least one image", Toast.LENGTH_SHORT).show();
                } else {
                    AddDataBmob.addDataToNote(title, context, pathList, getCheckData);
                    finish();
                }
                break;
            case R.id.note_add_pic:
                pathList.clear();
                ImgSelActivity.startActivity(this, config, REQUEST_CODE);  // Open the picture selector
                break;
            case R.id.note_sneaker:
            case R.id.note_fashion:
            case R.id.note_trip:
            case R.id.note_food:
            case R.id.note_game:
            case R.id.note_movie:
            case R.id.note_music:
            case R.id.note_pc:
            case R.id.note_phone:
                checkLimit();
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            pathList = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
            for (String path : pathList) {
                addView(path);
            }
        }
    }

    private void addView(final String s) {
        linearLayout = findViewById(R.id.linearlayout);
        final SketchImageView img = new SketchImageView(this);
        TextView textView = new TextView(this);
        img.setLayoutParams(new LinearLayout.LayoutParams(300, 300));
        textView.setLayoutParams(new LinearLayout.LayoutParams(20, 300));
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        img.displayImage(s);
        linearLayout.addView(textView);
        linearLayout.addView(img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoteEditView.this, NoteEditShowBigPic.class);
                intent.putExtra("showbigpic", s);
                startActivity(intent);
                overridePendingTransition(R.anim.showbigpic_in, R.anim.showbigpic_out);
            }
        });
        img.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                popDeleteDialog(view, s);
                return true;
            }
        });
    }

    private void popDeleteDialog(final View view, final String s) {
        new AlertDialog.Builder(this)
                .setTitle("Delete this image?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        linearLayout.removeView(view);
                        pathList.remove(s);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
    }

    private void addCheckData() {
        getCheckData.clear();
        for (int i = 0; i < checkItem.size(); i++) {
            if (checkItem.get(i).isChecked()) {
                getCheckData.add(strings[i]);
            }
        }
    }

    //    private void deleteImage(View view){
//        for(int i = 0;i<addImageList.size();i++){
//            if(addImageList.get(i)==view){
//                linearLayout.removeViewAt();
//            }
//        }
//    }
    //set the number limitation of words
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        limit.setHint((30 - noteTitle.getText().toString().length()) + "");
    }

    public void checkLimit() {
        //check limit number
        int check = 0;
        for (CheckBox box : checkItem) {
            if (box.isChecked() && check < 3) {
                check++;
            }
        }
        for (CheckBox box : checkItem) {
            if (!box.isChecked() && check == 3) {
                box.setClickable(false);
            } else if (check < 3) {
                box.setClickable(true);
            }
        }
    }
}
