package com.imgsel.common;


import com.imgsel.bean.Folder;

/**
 * @author yuyh.
 * @date 2016/8/5.
 */
public interface OnFolderChangeListener {

    void onChange(int position, Folder folder);
}
