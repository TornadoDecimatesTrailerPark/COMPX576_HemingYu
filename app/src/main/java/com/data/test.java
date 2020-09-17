/*
package com.data;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int space = 5;//设置RecyclerView控件的item的上下间距
    //要展示的对应item的数据，imgs是上方的图片/视图
    //titles是标题，headsIcon是头像，username是用户名
    private int[] imgs = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img4};
    private String[] titles = {"电影\n看书的女人", "阿呆的沙雕绘画", "帅猫", "夕阳下的教学楼", "看不见的"};
    private int[] headsIcon = {R.drawable.user, R.drawable.files_txt, R.drawable.add_friends, R.drawable.clock_unchecked, R.drawable.community_unchecked};
    private String[] usernames = {"阿呆", "Wacke", "肉团", "加密", "ajia"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);//设置刚写的xml文件页面

        HomeAdapter homeAdapter = new HomeAdapter();//创建适配器对象

        RecyclerView recyclerView1 = findViewById(R.id.home_item);//创建recyclerView对象，并初始化其xml文件
        recyclerView1.setLayoutManager(new GridLayoutManager(this, 2));//设置为表格布局，列数为2（这个是最主要的，就是这个来展示陈列式布局）
        recyclerView1.addItemDecoration(new space_item(space));//给recycleView添加item的间距
        recyclerView1.setAdapter(homeAdapter);//将适配器添加到recyclerView
    }

    public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
        @NonNull
        @Override
        //加载布局文件并返回MyViewHolder对象
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //创建view对象
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.home_item, parent, false);
            //创建MyViewHolder对象
            MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
        }

        @Override
        //获取数据并显示到对应控件
        public void onBindViewHolder(@NonNull HomeAdapter.MyViewHolder holder, int position) {
            //给我的四个控件获取一下数据，注意不同类型调用不同的方法，设置图片用setImageResource（），设置文字用setText（）
            holder.img.setImageResource(imgs[position]);
            holder.title.setText(titles[position]);
            holder.head.setImageResource(headsIcon[position]);
            holder.username.setText(usernames[position]);
        }

        @Override
        public int getItemCount() {
            //获取列表条目总数
            return titles.length;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            //初始化控件
            ImageView img, head;
            TextView title, username;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                img = itemView.findViewById(R.id.home_item_img);
                title = itemView.findViewById(R.id.home_item_title);
                head = itemView.findViewById(R.id.home_item_head);
                username = itemView.findViewById(R.id.home_item_username);
            }
        }
    }

    public class space_item extends RecyclerView.ItemDecoration {
        //设置item的间距
        private int space = 5;

        public space_item(int space) {
            this.space = space;
        }

        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.bottom = space;
            outRect.top = space;
        }
    }
}*/
