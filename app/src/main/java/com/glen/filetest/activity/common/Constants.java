package com.glen.filetest.activity.common;

import android.Manifest;

import java.io.File;
import java.util.ArrayList;

public class Constants {

    public static final int RC_STORAGE_PERM = 122;
    public static final String[] StoragePerms2 = {Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};

    public static ArrayList<File> getPDFfile(File dir) {
        ArrayList<File> fileList = new ArrayList<File>();
        File listFile[] = dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (int i = 0; i < listFile.length; i++) {
                if (listFile[i].isDirectory()) {
                    //fileList.add(listFile[i]);
                    getPDFfile(listFile[i]);

                } else {
                    if (listFile[i].getName().endsWith(".pdf"))
                    {
                        fileList.add(listFile[i]);
                    }
                }

            }
        }
        return fileList;
    }

    public static  ArrayList<File> getDOCfile(File dir) {
        ArrayList<File> fileList = new ArrayList<File>();
        File listFile[] = dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (int i = 0; i < listFile.length; i++) {

                if (listFile[i].isDirectory()) {
                    //fileList.add(listFile[i]);
                    getDOCfile(listFile[i]);

                } else {
                    if (listFile[i].getName().endsWith(".doc")||
                            listFile[i].getName().endsWith(".docx"))
                    {
                        fileList.add(listFile[i]);
                    }
                }

            }
        }
        return fileList;
    }
}
