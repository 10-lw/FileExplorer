package com.onezero.filemanager.fileContent;

import android.view.View;

import com.onezero.filemanager.ContentAdapter;
import com.onezero.filemanager.FileInfo;
import com.onezero.filemanager.RecyclerVieAdapterCallBack;
import com.onezero.filemanager.ScanCallback;
import com.onezero.filemanager.fragments.BaseContentFragment;
import com.onezero.filemanager.utils.FileScanUtils;

import java.util.List;

public class FilesContentFragment extends BaseContentFragment {
    @Override
    public void onResume() {
        super.onResume();
        setFileDates();
    }

    public void setFileDates() {
        FileScanUtils.scanFiles(getContext(), null, new ScanCallback() {
            @Override
            public void scanFilesFinish(List<FileInfo> commonFiles) {
                setDates(commonFiles);
            }

            @Override
            public void scanApksFinish(List<FileInfo> commonFiles) {
            }
        });
    }

    public void setDates(final List<FileInfo> fileList) {
        if (setViewVisibilityState(fileList)) {
            return;
        }
        loadingText.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        ContentAdapter contentAdapter = new ContentAdapter(getContext(), fileList, new RecyclerVieAdapterCallBack() {
            @Override
            public void onListSizeChange(List<FileInfo> infos) {
                setViewVisibilityState(infos);
            }
        });
        recyclerView.setAdapter(contentAdapter);
    }
}
