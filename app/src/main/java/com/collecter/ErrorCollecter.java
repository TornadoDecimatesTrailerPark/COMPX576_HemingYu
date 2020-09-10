package com.collecter;

import android.util.Log;

import cn.bmob.v3.exception.BmobException;


public class ErrorCollecter {
    public static String errorCode(BmobException e){
        int code = e.getErrorCode();
        switch (code){
            case 101: return "wrong username or password";
            case 108: return "username and password cannot be empty";
            case 109: return "username and password cannot be empty";
            case 202: return "user name already exists";
            case 203: return "mailbox already exists";
            case 204: return "mailbox cannot be empty";
            case 205: return "this user does not exist";
            case 150: return "file upload error";
            case 151: return "File deletion error";
            case 206: return "missing user rights";
            case 210: return "Old password is incorrect";
            case 9003: return "error uploading file";
            case 9004: return "failed to upload file";
            case 9007: return "File size over 10m";
            case 9008: return "upload file does not exist";
            case 9009: return "No cached data";
            case 9010: return "Network timeout";
            case 9011: return "user table does not support batch operations";
            case 9016: return "No network connection, please check your mobile phone network";
            case 9019: return "incorrect input format";
            case 1003: return "data has reached the upper limit";
            case 1005: return "the number of API requests has reached the limit";
            case 1500: return "upload file size exceeds limit";
            default:
                Log.i("bmob",e.getMessage());
                return "未知错误" + code;
        }
    }
}
