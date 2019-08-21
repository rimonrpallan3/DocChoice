package com.glen.filetest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.glen.filetest.R;

import java.io.File;
import java.util.ArrayList;

public class DocListAdapter extends RecyclerView.Adapter<DocListAdapter.ViewHolder> {

    ArrayList<File> fileList;

    public DocListAdapter(ArrayList<File> fileList) {
        this.fileList=fileList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.content_doc,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        File currentFile = fileList.get(position);
        holder.tvPdfHeading.setText(currentFile.getName());
        holder.tvCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return fileList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvPdfHeading;
        TextView tvCheckBox;
        ImageView ivPDFIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPdfHeading = itemView.findViewById(R.id.tvPdfHeading);
            tvCheckBox = itemView.findViewById(R.id.tvCheckBox);
            ivPDFIcon = itemView.findViewById(R.id.ivPDFIcon);

        }


    }
}