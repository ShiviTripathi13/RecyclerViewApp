package com.shivangi.recyclerviewapp;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.webkit.MimeTypeMap;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileView {
    public static List<File> getAllFiles(Context context){
        List<File> allFiles = new ArrayList<>();

        ContentResolver contentResolver = context.getContentResolver();
        Uri uri = MediaStore.Files.getContentUri("external");

        String[] projection = {
                MediaStore.Files.FileColumns.TITLE,
                MediaStore.Files.FileColumns.MIME_TYPE,
                MediaStore.Files.FileColumns.DATE_ADDED
        };

        String selection = MediaStore.Files.FileColumns.MIME_TYPE + "=? or "
                +MediaStore.Files.FileColumns.MIME_TYPE + "=?";

        String[] selectionArgs = new String[]{
                MimeTypeMap.getSingleton().getMimeTypeFromExtension("pdf"),
                MimeTypeMap.getSingleton().getMimeTypeFromExtension("txt"),
                MimeTypeMap.getSingleton().getMimeTypeFromExtension("zip")
        };

        String sortingOrder = MediaStore.Files.FileColumns.DATE_ADDED + " DESC";

        Cursor cursor = contentResolver.query(uri, projection, selection, selectionArgs, sortingOrder);

        if(cursor != null){
            try{
                int titleIndex = cursor.getColumnIndex(MediaStore.Files.FileColumns.TITLE);

                while (cursor.moveToNext()) {
                    String filePath = cursor.getString(titleIndex);
                    allFiles.add(new File(filePath));
                }
             } finally {
                cursor.close();
            }
        }
        return allFiles;
    }
}
