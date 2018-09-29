package com.onezero.filemanager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.onezero.filemanager.appContent.ApplicationHelper;
import com.onezero.filemanager.utils.FileUtils;

import java.io.File;
import java.util.List;

public class ContentAdapter extends RecyclerView.Adapter<FileItemHolder> {
    private List<FileInfo> mList;
    private RecyclerVieAdapterCallBack mCallback;
    public Context mContext;

    public ContentAdapter(Context context, List<FileInfo> list, RecyclerVieAdapterCallBack callBack) {
        this.mContext = context;
        this.mList = list;
        this.mCallback = callBack;
    }

    @NonNull
    @Override
    public FileItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.file_item_layout, viewGroup, false);
        FileItemHolder fileItemHolder = new FileItemHolder(view);
        return fileItemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FileItemHolder fileItemHolder, final int i) {
        final FileInfo info = mList.get(i);
        fileItemHolder.fileName.setText(info.getFileName());
        long fileSize = info.getFileSize();
        String size = FileUtils.getLength(fileSize);
        fileItemHolder.fileSize.setText(size);
        if (info.isApk()) {
            fileItemHolder.icon.setImageDrawable(info.getIconId());
        } else {
            fileItemHolder.icon.setImageResource(info.getFileIcon());
        }
        fileItemHolder.actionButton.setText(info.isApk() ? (info.isInstalled() ? R.string.have_been_installd : R.string.install_app) : R.string.remove_file);
        fileItemHolder.itemView.setTag(info.getFilePath());
        fileItemHolder.actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (info.isApk()) {
                    if (info.isInstalled()) {
                        Toast.makeText(mContext, R.string.app_have_installed, Toast.LENGTH_LONG).show();
                        return;
                    }
                    ApplicationHelper.installApkByPath(mContext, info.getFilePath());
                } else {
                    removeFiles(info, i);
                }
            }
        });
    }

    private void removeFiles(FileInfo info, int i) {
        File file = new File(info.getFilePath());
        if (file.exists() && file.isFile()) {
            boolean delete = file.delete();
            if (delete) {
                mList.remove(i);
                notifyDataSetChanged();
                if (mList.size() == 0 && mCallback != null) {
                    mCallback.onListSizeChange(mList);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }
}
