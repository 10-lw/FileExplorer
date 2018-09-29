package com.onezero.filemanager;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FileItemHolder extends RecyclerView.ViewHolder {

    public TextView fileName;
    public Button actionButton;
    public ImageView icon;
    public TextView fileSize;

    public FileItemHolder(@NonNull View itemView) {
        super(itemView);
        icon = itemView.findViewById(R.id.item_icon);
        fileName = itemView.findViewById(R.id.file_name);
        fileSize = itemView.findViewById(R.id.file_size);
        actionButton = itemView.findViewById(R.id.install_or_remove);
    }
}
