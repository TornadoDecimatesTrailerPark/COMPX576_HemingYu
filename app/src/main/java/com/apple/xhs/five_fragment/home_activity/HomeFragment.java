package com.apple.xhs.five_fragment.home_activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.apple.util.MyFragmentPagerAdapter;
import com.apple.xhs.R;
import com.apple.xhs.five_fragment.home_activity.home_fragment.Notes;
import com.apple.xhs.searchwhole.SearchMain;

import java.util.ArrayList;
import java.util.List;




public class HomeFragment extends Fragment implements View.OnClickListener{
    TextView main_search_bar;
    View popUpView,homeTop;
    ImageView openCamera;
    
    ViewPager viewPager;
    PopupWindow popupWindow;
    WindowManager windowManager;
    MyFragmentPagerAdapter adapter;
    
    List<Fragment> data = new ArrayList<>();
    List<String> list = new ArrayList<>();

    Notes Notes;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_layout,container,false);
        initViewPopWindow(view);
        initOthersView(view);
        initViewPager(view);
        setViewListener();
        return view;
    }

    private void initOthersView(View view) {
        main_search_bar = view.findViewById(R.id.main_search_bar);
        openCamera = view.findViewById(R.id.home_open_camera);

    }

    private void setViewListener() {
        openCamera.setOnClickListener(this);
        main_search_bar.setOnClickListener(this);
    }

    private void initViewPager(View view) {
        viewPager = view.findViewById(R.id.home_viewpager);
        getFragment();
        adapter = new MyFragmentPagerAdapter(getChildFragmentManager(),data,list);
        addAllFragment();
        viewPager.setAdapter(adapter);
    }

    private void addAllFragment() {
        adapter.addFragment(Notes,"AA");
    }

    private void getFragment() {
        Notes = new Notes();
     
    }

    //pop uo window
    private void initViewPopWindow(View view) {
        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        windowManager = getActivity().getWindowManager();
        popupWindow = new PopupWindow(popUpView,windowManager.getDefaultDisplay().getWidth(),
                windowManager.getDefaultDisplay().getHeight());
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);

        homeTop = view.findViewById(R.id.home_top);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main_search_bar:
                startActivity(new Intent(getActivity(), SearchMain.class));
                break;
            case R.id.home_open_camera:
                startActivity(new Intent(getActivity(), HomeOpenCamera.class));
                getActivity().overridePendingTransition(R.anim.home_camera_open,R.anim.home_camera_close);
                break;
        }
    }
}
