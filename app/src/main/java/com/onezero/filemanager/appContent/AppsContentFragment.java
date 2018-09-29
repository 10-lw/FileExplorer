package com.onezero.filemanager.appContent;

import android.util.Log;
import android.view.View;

import com.onezero.filemanager.ContentAdapter;
import com.onezero.filemanager.FileInfo;
import com.onezero.filemanager.ScanCallback;
import com.onezero.filemanager.fragments.BaseContentFragment;
import com.onezero.filemanager.utils.FileScanUtils;

import java.util.List;

public class AppsContentFragment extends BaseContentFragment {

    @Override
    public void onResume() {
        super.onResume();
        setFileDates();
    }


    public void setFileDates() {
        FileScanUtils.scanFiles(getContext(), FileScanUtils.APK_EXTENTION, new ScanCallback() {
            @Override
            public void scanFilesFinish(List<FileInfo> commonFiles) {
            }

            @Override
            public void scanApksFinish(List<FileInfo> apkFiles) {
                setDates(apkFiles);
            }
        });
    }

    public void setDates(final List<FileInfo> appList) {
        if (setViewVisibilityState(appList)) {
            return;
        }
        loadingText.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        ContentAdapter adapter = new ContentAdapter(getContext(), appList, null);
        recyclerView.setAdapter(adapter);
    }
}
