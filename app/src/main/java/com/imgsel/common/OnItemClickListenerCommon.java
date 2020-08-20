package com.imgsel.common;


import com.imgsel.bean.Image;

/**
 * @author yuyh.
 * @date 2016/8/5.
 */
public interface OnItemClickListenerCommon {

    int onCheckedClick(int position, Image image);

    void onImageClick(int position, Image image);
}
