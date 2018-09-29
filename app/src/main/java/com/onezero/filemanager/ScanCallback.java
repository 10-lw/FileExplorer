package com.onezero.filemanager;

import java.util.List;

public interface ScanCallback {
    void scanFilesFinish( List<FileInfo> commonFiles);
    void scanApksFinish( List<FileInfo> apkFiles);

}
