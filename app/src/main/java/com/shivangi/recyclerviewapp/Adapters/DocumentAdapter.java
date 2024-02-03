package com.shivangi.recyclerviewapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shivangi.recyclerviewapp.R;

import java.io.File;
import java.util.List;

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.ViewHolder> {

    private static List<File> documentFiles;

    public DocumentAdapter(List<File> documentFiles) {
        this.documentFiles = documentFiles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_document, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        File documentFile = documentFiles.get(position);
        holder.fileNameTextView.setText(documentFile.getName());
        holder.filePath.setText(documentFile.getAbsolutePath());
        holder.bindData(documentFile);
    }

    @Override
    public int getItemCount() {
        return documentFiles.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView fileNameTextView;
        TextView filePath;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
//            fileName = itemView.findViewById(R.id.txtFileName);
            filePath = itemView.findViewById(R.id.FilePath);
            fileNameTextView = itemView.findViewById(R.id.fileNameTextView);
        }

        void bindData(File documentFile) {

            fileNameTextView.setText(documentFile.getName());
        }
    }
    public void addItem(File file) {
        documentFiles.add(0, file);  // Add to the top of the list (newest on top)
        notifyItemInserted(0);
    }

}

