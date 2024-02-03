package com.shivangi.recyclerviewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shivangi.recyclerviewapp.Adapters.DocumentAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
     DocumentAdapter adapter;
    private static final int PICK_FILE_REQUEST_CODE = 100;
    private Button SelectFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        SelectFile = findViewById(R.id.selectFile);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set click listener for the button to pick a file
        SelectFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFilePicker();
            }
        });

        List<File> fileList = getDocumentFiles();
        adapter = new DocumentAdapter(fileList);
        recyclerView.setAdapter(adapter);
    }

    private List<File> getDocumentFiles() {
        List<File> documents = new ArrayList<>();

        File storageDir = Environment.getExternalStorageDirectory();
        File[] files = storageDir.listFiles();

        if (files != null) {
            for (File file : files) {
                if (isDocumentFile(file)) {
                    documents.add(file);
                }
            }

            // Sort files by last modified time in descending order (newest on top)
            Collections.sort(documents, new Comparator<File>() {
                @Override
                public int compare(File file1, File file2) {
                    return Long.compare(file2.lastModified(), file1.lastModified());
                }
            });
        }

        return documents;
    }

    private boolean isDocumentFile(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".pdf") || name.endsWith(".txt") || name.endsWith(".zip");
    }

    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");  // Set MIME type to filter files
        startActivityForResult(intent, PICK_FILE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FILE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri selectedFileUri = data.getData();
            String filePath = getPathFromUri(selectedFileUri);
            if (filePath != null) {
                // Add the selected file to the RecyclerView
                File selectedFile = new File(filePath);
                adapter.addItem(selectedFile);
            }
        }
    }

    private String getPathFromUri(Uri uri) {
        String[] projection = {OpenableColumns.DISPLAY_NAME};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            cursor.moveToFirst();
            String fileName = cursor.getString(nameIndex);
            cursor.close();
            return fileName;
        }
        return null;
    }
}
