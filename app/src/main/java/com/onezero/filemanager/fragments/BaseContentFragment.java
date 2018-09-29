package com.onezero.filemanager.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.onezero.filemanager.AppInfo;
import com.onezero.filemanager.ContentAdapter;
import com.onezero.filemanager.FileInfo;
import com.onezero.filemanager.R;

import java.util.List;


public abstract class BaseContentFragment extends Fragment {
    public RecyclerView recyclerView;
    public TextView loadingText;
    public TextView apkName;
    public TextView fileSize;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_fragment_layout, container, false);
        initViews(view);
        return view;
    }

    public void initViews(View view){
        recyclerView = view.findViewById(R.id.recycler_view_content);
        loadingText = view.findViewById(R.id.loading);
        loadingText.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
        apkName = view.findViewById(R.id.file_name);
        fileSize = view.findViewById(R.id.file_size);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public boolean setViewVisibilityState(List<FileInfo> list) {
        if (list == null || list.size() == 0) {
            loadingText.setText(R.string.no_files);
            loadingText.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            return true;
        }
        return false;
    }

}
