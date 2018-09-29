package com.onezero.filemanager;

import android.content.Intent;
import android.graphics.drawable.Drawable;

/**
 * Created by lizeiwei on 2018/7/28.
 */

public class AppInfo {
    private String appLabel;    //应用程序标签
    private Drawable appIconId ;  //应用程序图像
    private float apkSize;
    private String apkPath;
    private boolean isInstalled;

    public boolean isInstalled() {
        return isInstalled;
    }

    public void setInstalled(boolean installed) {
        isInstalled = installed;
    }

    public String getAppLabel() {
        return appLabel;
    }

    public void setAppLabel(String appLabel) {
        this.appLabel = appLabel;
    }

    public Drawable getAppIconId() {
        return appIconId;
    }

    public void setAppIconId(Drawable appIconId) {
        this.appIconId = appIconId;
    }

    public float getApkSize() {
        return apkSize;
    }

    public void setApkSize(float apkSize) {
        this.apkSize = apkSize;
    }

    public String getApkPath() {
        return apkPath;
    }

    public void setApkPath(String apkPath) {
        this.apkPath = apkPath;
    }
}
