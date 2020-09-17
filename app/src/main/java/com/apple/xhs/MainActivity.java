package com.apple.xhs;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.apple.initbmob.InitBmob;
import com.apple.util.MyRecyclerViewAdapter;
import com.apple.xhs.custom_view.HomeCamera;
import com.apple.xhs.custom_view.MineUserInfoSetting;
import com.bean.Note;
import com.collecter.ActivityCollecter;
import com.base.BaseActivity;
import com.collecter.ErrorCollecter;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;


public class MainActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tag_main_page)
    TextView tag_main_page;
    @BindView(R.id.tag_personal_info)
    TextView tag_personal_info;
    @BindView(R.id.tag_new_notes)
    ImageView tag_new_notes;
    @BindView(R.id.home_open_camera)
    ImageView home_open_camera;
    List<Note> data = new ArrayList<>();


    private int[] imgs = {R.mipmap.arrow_icon_right, R.mipmap.arrow_icon_right, R.mipmap.arrow_icon_right, R.mipmap.arrow_icon_right, R.mipmap.arrow_icon_right,};
    private String[] titles = {"aaa", "bbb", "ccc", "ddd", "eee"};
    private String[] usernames = {"asda", "Wacke", "asd", "asd", "ajia"};

int dataSize=0;
    long exitTime;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        selectAllNote();
        ActivityCollecter.finishOthers();

        HomeAdapter homeAdapter = new HomeAdapter();
        RecyclerView recyclerView1 = findViewById(R.id.home_item);
        recyclerView1.setLayoutManager(new GridLayoutManager(this, 2));//设置为表格布局，列数为2（这个是最主要的，就是这个来展示陈列式布局）
        int space = 5;
        recyclerView1.addItemDecoration(new space_item(space));
        recyclerView1.setAdapter(homeAdapter);//将适配器添加到recyclerView

        initView();
    }

    @Override
    public int getContentViewId() {
        return R.layout.main_activity;
    }

    @SuppressLint("ResourceAsColor")
    private void initView() {
        resetFragment();
        tag_main_page.setSelected(true);
        tag_main_page.setOnClickListener(this);
        tag_personal_info.setOnClickListener(this);
        tag_new_notes.setOnClickListener(this);
        home_open_camera.setOnClickListener(this);




    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View view) {
        resetTabColor();
        resetFragment();
        switch (view.getId()) {
            case R.id.tag_personal_info:
                startActivity(new Intent(MainActivity.this, MineUserInfoSetting.class));
                break;
            case R.id.tag_new_notes:
            case R.id.home_open_camera:
                startActivity(new Intent(MainActivity.this, HomeCamera.class));
                /*     Toast.makeText(this,"<clink, clink>", Toast.LENGTH_SHORT).show();*/
                break;
        }
    }

    @SuppressLint("ResourceAsColor")
    public void resetTabColor() {
        tag_main_page.setSelected(false);
        tag_personal_info.setSelected(false);

    }

    public void resetFragment() {


    }


    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            Toast.makeText(this, "Click once to quit", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            ActivityCollecter.finishAll();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>{
        @NonNull
        @Override
        //加载布局文件并返回MyViewHolder对象
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //创建view对象
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.main_item,parent,false);

            //创建MyViewHolder对象
            MyViewHolder myViewHolder=new MyViewHolder(view);
            return myViewHolder;
        }

        @Override
        //获取数据并显示到对应控件
        public void onBindViewHolder(@NonNull HomeAdapter.MyViewHolder holder, int position) {
            //给我的四个控件获取一下数据，注意不同类型调用不同的方法，设置图片用setImageResource（），设置文字用setText（）
            holder.img.setImageResource(imgs[position]);
            holder.title.setText(titles[position]);
            holder.username.setText(usernames[position]);
        }

        @Override
        public int getItemCount() {
            //获取列表条目总数
            return titles.length;
        }
        class MyViewHolder extends RecyclerView.ViewHolder{
            //初始化控件
            ImageView img,head;
            TextView title,username;
            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                img=itemView.findViewById(R.id.home_item_img);
                title=itemView.findViewById(R.id.home_item_title);
                username=itemView.findViewById(R.id.home_item_username);
            }
        }
    }
    public class space_item extends RecyclerView.ItemDecoration{
        //设置item的间距
        private int space=5;
        public space_item(int space){
            this.space=space;
        }
        public void getItemOffsets(Rect outRect,View view,RecyclerView parent,RecyclerView.State state){
            outRect.bottom=space;
            outRect.top=space;
        }
    }


}
