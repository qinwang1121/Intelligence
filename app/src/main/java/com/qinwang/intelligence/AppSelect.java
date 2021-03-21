package com.qinwang.intelligence;

/**
 * @Auther:haoyanwang1121@gmail.com
 * @Date:2021/3/18
 * @Description:com.qinwang.intelligence
 * @Version:1.0
 * @function:
 */
public class AppSelect {
    private static String appUser = null;

    public static String getAppUser() {
        if (BuildConfig.PURPOSE.equals("admin")){
            appUser = "admin";
        }else if (BuildConfig.PURPOSE.equals("user")){
            appUser = "user";
        }
        return appUser;
    }

    public void setAppUser(String appUser) {
        this.appUser = appUser;
    }
}
