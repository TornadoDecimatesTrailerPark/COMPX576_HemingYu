package com.apple.util;

import androidx.viewpager.widget.PagerAdapter;

import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.xiaopan.sketch.SketchImageView;

//This is the adaptive class of the Sketch plug-in, and Sketch is used to make the picture adaptive in size and scale.
public class MySketchViewPagerAdapter extends PagerAdapter {
    List<SketchImageView> list;

    public MySketchViewPagerAdapter(List<SketchImageView> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(list.get(position));
        return list.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list.get(position));
    }
}
