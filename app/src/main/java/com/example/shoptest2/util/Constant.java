package com.example.shoptest2.util;

import okhttp3.MediaType;

public final class Constant {
    /**
     * url*/
    public static  final  String parentUrl="http://112.125.120.236:8080/";
    /**
     * 用户信息*/
    public static String userName;
    /***/
    public static String userId;
    /**
     * 结果码，成功*/
    public static final  int result_success=1;

    /**
     * 结果码 ,失败*/
    public  static final  int result_failed=2;
    /**结果码 ,返回*/
    public static  final  int result_back=3;
    /**
     * 忘记密码请求吗*/
    public final static int forgetPasswordCode=1;
    /**
     * 注册密码请求吗*/
    public final static int  registerCode=2;

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        Constant.userName = userName;
    }

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String userId) {
        Constant.userId = userId;
    }
}
