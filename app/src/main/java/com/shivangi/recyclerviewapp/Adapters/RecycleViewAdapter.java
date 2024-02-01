package com.shivangi.recyclerviewapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shivangi.recyclerviewapp.R;

import java.io.File;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.FileViewHolder> {

    private List<File> fileList;
    private Context context;

    public RecycleViewAdapter(Context context, List<File> fileList) {
        this.context = context;
        this.fileList = fileList;
    }

    @NonNull
    @Override
    public FileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycle_view, parent, false);
        return new FileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FileViewHolder holder, int position) {
        File file = fileList.get(position);
        holder.textViewFileName.setText(file.getName());
    }

    @Override
    public int getItemCount() {
        return fileList.size();
    }

    public class FileViewHolder extends RecyclerView.ViewHolder {
        TextView textViewFileName;

        public FileViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewFileName = itemView.findViewById(R.id.textViewFileName);
        }
    }
}

