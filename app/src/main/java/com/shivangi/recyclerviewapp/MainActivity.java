package com.shivangi.recyclerviewapp;

import com.shivangi.recyclerviewapp.FileView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.shivangi.recyclerviewapp.Adapters.RecycleViewAdapter;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
//    RecyclerView recyclerView = findViewById(R.id.recyclerView);

    List<File> recyclerViewAllFiles = FileView.getAllFiles(MainActivity.this);
    RecyclerView recycleView = findViewById(R.id.recyclerView);
    ArrayAdapter<File> adapter = new ArrayAdapter<>(this,
            android.R.layout.simple_list_item_1, recyclerViewAllFiles);
    recycleView.setAdapter(adapter);


}