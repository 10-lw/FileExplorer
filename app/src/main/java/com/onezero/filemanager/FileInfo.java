package com.onezero.filemanager;

import android.graphics.drawable.Drawable;

public class FileInfo {
    private String fileName;
    private long fileSize;
    private String fileExtention;
    private String filePath;
    private boolean isInstalled;
    private boolean isApk;
    private Drawable iconId ;  //icon
    private int fileIcon;

    public int getFileIcon() {
        return fileIcon;
    }

    public void setFileIcon(int fileIcon) {
        this.fileIcon = fileIcon;
    }

    public Drawable getIconId() {
        return iconId;
    }

    public void setIconId(Drawable iconId) {
        this.iconId = iconId;
    }

    public boolean isApk() {
        return isApk;
    }

    public void setApk(boolean apk) {
        isApk = apk;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileExtention() {
        return fileExtention;
    }

    public void setFileExtention(String fileExtention) {
        this.fileExtention = fileExtention;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public boolean isInstalled() {
        return isInstalled;
    }

    public void setInstalled(boolean installed) {
        isInstalled = installed;
    }

}
