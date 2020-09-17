package com.apple.xhs.custom_view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.apple.xhs.R;
import com.base.BaseActivity;
import com.bean.MyUser;
import com.data.UpdateDataBmob;


import org.feezu.liuli.timeselector.TimeSelector;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import me.panpf.sketch.SketchImageView;
import me.panpf.sketch.process.CircleImageProcessor;
import me.panpf.sketch.request.DisplayOptions;


/*import static com.data.AddDataBmob.compressBitmap;*/



public class MineUserInfoSetting extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.infosetting)
    InfoSettingTitle back;
    @BindView(R.id.override_name)
    UserInfoRow name;
    @BindView(R.id.override_id)
    UserInfoRow id;
    @BindView(R.id.birthday_choice)
    UserInfoRow birthday;
    @BindView(R.id.sex_choice)
    UserInfoRow sex;
    @BindView(R.id.signatures_edit)
    UserInfoRow signatures;
    @BindView(R.id.override_head)
    View head;
    @BindView(R.id.img_user_head)
    SketchImageView head_icon;
    @BindView(R.id.area_choice)
    UserInfoRow area;
    String sex_dialog;
    String currentName;
    String currentId;
    String currentArea;
    String currentSignatures;
    boolean sexDialog = false;
    List<Map<Integer,String>> skinData = new ArrayList<>();
    Map<Integer,String> map = new HashMap<>();

    @Override
    public int getContentViewId() {
        return R.layout.mine_user_info;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHeadIconCircle();
        refreshUserInfo();
        setViewListener();

    }
    public void setHeadIconCircle(){
        DisplayOptions displayOptions = new DisplayOptions();
        displayOptions.setProcessor(CircleImageProcessor.getInstance());
        head_icon.setOptions(displayOptions);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case 0:

                break;
            case 1:
                String newname = data.getStringExtra("name");
                if(newname.equals("")){
                    return;
                }
                name.getName().setText(newname);
                UpdateDataBmob.UpdataNickname(newname);
                break;
            case 2:
                String newid = data.getStringExtra("id");
                if(newid.equals("")){
                    return;
                }
                id.getName().setText(newid);
                UpdateDataBmob.UpdataID(newid);
                break;
            case 3:
                String newArea = data.getStringExtra("mylocation");
                if(newArea.equals("")){
                    return;
                }
                area.getName().setText(newArea);
                UpdateDataBmob.UpdataArea(newArea);
                break;
            case 4:
                String newsign = data.getStringExtra("sign");
                if(newsign.equals("")){
                    return;
                }
                signatures.getName().setText(newsign);
                UpdateDataBmob.UpdataSignature(newsign);
                break;
            default:
                String img_url=uriChange(data);
                String tempPath = Environment.getExternalStorageDirectory().getPath()
                        /*+ "/XHS/temp/" + System.currentTimeMillis() */+ ".jpg";
              /*  compressBitmap(img_url,tempPath);*/
                Log.i("bmob","Url:" + tempPath);
                head_icon.displayImage(tempPath);

                BmobFile icon = new BmobFile(new File(tempPath));
                UpdateDataBmob.UpdataHead(icon);
                break;
        }
    }

    //将图片URI转换成存储路径
    public String uriChange(Intent data){
        Uri originalUri=data.getData();
        String []imgs1={MediaStore.Images.Media.DATA};
        Cursor cursor=this.managedQuery(originalUri, imgs1, null, null, null);
        int index=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(index);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void setViewListener() {
        back.setImgListener(this);
        name.setOnClickListener(this);
        id.setOnClickListener(this);
        sex.setOnClickListener(this);
        head.setOnClickListener(this);
        area.setOnClickListener(this);
        signatures.setOnClickListener(this);
    }
    @OnClick(R.id.birthday_choice)
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.my_setting_back:
                finish();
                break;
            case R.id.override_head:
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.addCategory(Intent.CATEGORY_OPENABLE);
                galleryIntent.setType("image/*");//图片
                startActivityForResult(galleryIntent, 100);
                break;
            case R.id.override_name:
                currentName = name.getName().getText().toString();
                Intent intentName = new Intent(this,MineSettingName.class);
                intentName.putExtra("currentname",currentName);
                startActivityForResult(intentName,1);
                break;
            case R.id.override_id:
                currentId = id.getName().getText().toString();
                Intent intentId = new Intent(this,MineSettingID.class);
                intentId.putExtra("currentid",currentId);
                startActivityForResult(intentId,2);
                break;
            case R.id.sex_choice:
                sexChoiceDialog();
                break;
            case R.id.birthday_choice:
                setBirthday();
                break;
            //个性签名
            case R.id.signatures_edit:
                currentSignatures = signatures.getName().getText().toString();
                Intent intentSign = new Intent(this,MineSettingSign.class);
                intentSign.putExtra("sign",currentSignatures);
                startActivityForResult(intentSign,4);
                break;

        }
    }



    private void setBirthday() {
        TimeSelector timeSelector = new TimeSelector(this, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String time) {
                String date = time.substring(0,10);
                birthday.getName().setText(date);
                UpdateDataBmob.UpdataBirthday(date);
            }
        },"1970-01-01 00:00","2030-12-31 00:00");
        timeSelector.setTitle("Please choose your birthday");
        timeSelector.setMode(TimeSelector.MODE.YMD);
        timeSelector.show();
    }
    //性别选择
    private void sexChoiceDialog() {

        new AlertDialog.Builder(this)
                .setTitle("Please choose your gender")
                .setSingleChoiceItems(new String[]{"Male", "Famale"}, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(i == 0){
                            sex_dialog = "Male";
                            sexDialog = false;
                        }else if(i == 1){
                            sex_dialog = "Female";
                            sexDialog = true;
                        }
                    }
                })
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        UpdateDataBmob.UpdataSex(sexDialog);
                        sex.getName().setText(sex_dialog);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
    }

    //从数据库获取个人信息（创建和刷新时调用）
    private void refreshUserInfo(){
        MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
        if(myUser.getHead()!=null){
            //加载头像
            head_icon.displayImage(myUser.getHead().getUrl());
        }

        //昵称
        String nickname_Bmob = myUser.getNickname();
        if (nickname_Bmob!=null){
            name.getName().setText(nickname_Bmob);
        }else {
            name.getName().setText("Hemingyu576");
        }

        //ID
        String id_Bmob = myUser.getCopyId();
        if (id_Bmob!=null){
            id.getName().setText(id_Bmob);
        }

        //性别
        Boolean sex_Bmob = myUser.getSex();
        if(sex_Bmob != null){
            sex.getName().setText(sex_Bmob ? "female" : "male");
        }

        //常住地
        String city_Bmob = myUser.getAddress();
        if(city_Bmob != null){
            area.getName().setText(city_Bmob);
        }

        //生日
        String birth_Bmob = myUser.getBirthday();
        if (birth_Bmob != null){
            birthday.getName().setText(birth_Bmob);
        }

        //个性签名
        String sign_Bmob = myUser.getSignature();
        if (sign_Bmob != null){
            signatures.getName().setText(sign_Bmob);
        }
    }
}
