package com.collecter;

import android.util.Log;

import cn.bmob.v3.exception.BmobException;



public class ErrorCollecter {
    public static String errorCode(BmobException e){
        int code = e.getErrorCode();
        switch (code){
            case 101: return "Wrong username or password";
            case 108: return "Username and password cannot be empty";
            case 109: return "Username and password cannot be empty";
            case 202: return "User name already exists";
            case 203: return "Email already exists";
            case 204: return "Email cannot be empty";
            case 205: return "User does not exist";
            case 150: return "File upload error";
            case 151: return "File deletion error";
            case 206: return "Missing user rights";
            case 210: return "Old password is incorrect";
            case 9003: return "Error uploading file";
            case 9004: return "Failed to upload file";
            case 9007: return "File size over 10m";
            case 9008: return "File does not exist";
            case 9009: return "No cached data";
            case 9010: return "Network timeout";
            case 9011: return "User table does not support batch operations";
            case 9016: return "No network connection, please check your mobile phone network";
            case 9019: return "Incorrect input format";
            case 1003: return "Data has reached the upper limit";
            case 1005: return "The number of API requests has reached the limit";
            case 1500: return "Upload file size exceeds limit";
            default:
                Log.i("bmob",e.getMessage());
                return "Unknown error" + code;
        }
    }
}
