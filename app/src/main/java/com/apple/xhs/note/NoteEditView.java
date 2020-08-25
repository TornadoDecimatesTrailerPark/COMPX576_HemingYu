package com.apple.xhs.note;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.apple.xhs.R;

import com.apple.xhs.custom_view.InfoSettingTitle;
import com.base.BaseActivity;
import com.bumptech.glide.Glide;
import com.data.AddDataBmob;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.imgsel.ImageLoader;
import com.imgsel.ImgSelActivity;
import com.imgsel.ImgSelConfig;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.panpf.sketch.SketchImageView;


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


    @BindView(R.id.note_nanren)
    CheckBox noteNanren;
    @BindView(R.id.note_hufu)
    CheckBox noteHufu;
    @BindView(R.id.note_jujia)
    CheckBox noteJujia;
    @BindView(R.id.note_shishang)
    CheckBox noteShishang;
    @BindView(R.id.note_meishi)
    CheckBox noteMeishi;
    @BindView(R.id.note_yundong)
    CheckBox noteYundong;
    @BindView(R.id.note_lvxing)
    CheckBox noteLvxing;
    @BindView(R.id.note_caizhuang)
    CheckBox noteCaizhuang;
    @BindView(R.id.note_muying)
    CheckBox noteMuying;

    String title;
    String context;
    List<String> getCheckData = new ArrayList<>();
    List<CheckBox> checkItem = new ArrayList<>();
    String[] strings = {"Phone", "Furniture", "shampoo", "clothes", "cosmetics", "food", "trip", "fashion", "sneaker"};
    LinearLayout linearLayout;
    String addrStr, province;
    boolean isShowArea = false;
    private List<String> pathList = new ArrayList<>();
    ImgSelConfig config;
    private static final int REQUEST_CODE = 0;

    @Override
    public int getContentViewId() {
        return R.layout.note_edit_view;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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

    //ImageSelector框架设置UI及功能
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
        checkItem.add(noteNanren);
        checkItem.add(noteHufu);
        checkItem.add(noteJujia);
        checkItem.add(noteShishang);
        checkItem.add(noteMeishi);
        checkItem.add(noteYundong);
        checkItem.add(noteLvxing);
        checkItem.add(noteCaizhuang);
        checkItem.add(noteMuying);
    }

    private void initViewListener() {
        //顶栏
        noteToolBar.setImgListener(this);
        noteToolBar.setDoneListener(this);
        //文本图片
        noteTitle.setOnClickListener(this);
        noteContext.setOnClickListener(this);
        noteAddPic.setOnClickListener(this);
        noteTitle.addTextChangedListener(this);
        noteContext.addTextChangedListener(this);
        //复选框
        noteNanren.setOnClickListener(this);
        noteHufu.setOnClickListener(this);
        noteJujia.setOnClickListener(this);
        noteShishang.setOnClickListener(this);
        noteMeishi.setOnClickListener(this);
        noteYundong.setOnClickListener(this);
        noteLvxing.setOnClickListener(this);
        noteCaizhuang.setOnClickListener(this);
        noteMuying.setOnClickListener(this);

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
                //addCheckData();//返回数据到 getCheckData；
                if (pathList.size() == 0) {
                    Toast.makeText(this, "请至少添加一张照片", Toast.LENGTH_SHORT).show();
                } else {
                    AddDataBmob.addDataToNote(title, context, pathList, getCheckData);
                    finish();
                }
                break;
            case R.id.note_add_pic:
                pathList.clear();
                ImgSelActivity.startActivity(this, config, REQUEST_CODE);  // 开启图片选择器
                break;
            case R.id.note_nanren:
            case R.id.note_hufu:
            case R.id.note_jujia:
            case R.id.note_shishang:
            case R.id.note_meishi:
            case R.id.note_yundong:
            case R.id.note_lvxing:
            case R.id.note_caizhuang:
            case R.id.note_muying:
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
                .setTitle("确定删除此图片吗？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        linearLayout.removeView(view);
                        pathList.remove(s);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
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
    //设置字数
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
        //感觉逻辑写的不好
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