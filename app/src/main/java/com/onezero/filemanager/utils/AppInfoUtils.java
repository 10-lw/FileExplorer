package com.onezero.filemanager.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;

import com.onezero.filemanager.AppInfo;
import com.onezero.filemanager.FileInfo;
import com.onezero.filemanager.QueryCallBack;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lw on 2018/7/28.
 */

public class AppInfoUtils {

    public static FileInfo getAppInfoByFile(PackageManager packageManager, File item) {
        FileInfo appInfo = null;
        try {
            appInfo = new FileInfo();
            String path = item.getAbsolutePath();
            appInfo.setIconId(getAppIcon(packageManager, path));
            appInfo.setFileName(getAppLabel(packageManager, path).toString());
            appInfo.setFileSize(item.length());
            appInfo.setFilePath(item.getAbsolutePath());
            appInfo.setApk(true);
            appInfo.setInstalled(isApplicationAvilible(packageManager, getPackageInfo(packageManager, path).packageName));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return appInfo;
    }

    /**
     * 判断手机是否安装某个应用
     * @param appPackageName  应用包名
     * @return   true：安装，false：未安装
     */
    public static boolean isApplicationAvilible(PackageManager pm, String appPackageName) {
        List<PackageInfo> pinfo = pm.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (appPackageName.equals(pn)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static Drawable getAppIcon(PackageManager pm, String apkFilepath) {
        PackageInfo pkgInfo = getPackageInfo(pm, apkFilepath);
        if (pkgInfo == null) {
            return null;
        }

        ApplicationInfo appInfo = pkgInfo.applicationInfo;
        if (Build.VERSION.SDK_INT >= 8) {
            appInfo.sourceDir = apkFilepath;
            appInfo.publicSourceDir = apkFilepath;
        }
        return pm.getApplicationIcon(appInfo);
    }

    //得到PackageInfo对象，其中包含了该apk包含的activity和service
    private static PackageInfo getPackageInfo(PackageManager pm, String apkFilepath) {
        PackageInfo pkgInfo = null;
        try {
            pkgInfo = pm.getPackageArchiveInfo(apkFilepath, PackageManager.GET_ACTIVITIES | PackageManager.GET_SERVICES);
        } catch (Exception e) {
            // should be something wrong with parse
            e.printStackTrace();
        }
        return pkgInfo;
    }

    private static CharSequence getAppLabel(PackageManager pm, String apkFilepath) {
        PackageInfo pkgInfo = getPackageInfo(pm, apkFilepath);
        if (pkgInfo == null) {
            return null;
        }
        ApplicationInfo appInfo = pkgInfo.applicationInfo;
        if (Build.VERSION.SDK_INT >= 8) {
            appInfo.sourceDir = apkFilepath;
            appInfo.publicSourceDir = apkFilepath;
        }

        return pm.getApplicationLabel(appInfo);
    }

}
