package com.glen.filetest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Environment;
import android.widget.LinearLayout;

import com.glen.filetest.adapter.DocListAdapter;
import com.glen.filetest.view.IMainView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IMainView {

    private File root;
    private ArrayList<File> fileList = new ArrayList<File>();
    private LinearLayout llView;
    RecyclerView rvPDFList;
    DocListAdapter docListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        llView = (LinearLayout) findViewById(R.id.llView);
        rvPDFList =  findViewById(R.id.rvPDFList);
        //getting SDcard root path
        root = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath());
        getfile(root);

        docListAdapter = new DocListAdapter(fileList);
        rvPDFList.setLayoutFrozen(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvPDFList.setLayoutManager(mLayoutManager);
        rvPDFList.setItemAnimator(new DefaultItemAnimator());
        rvPDFList.setAdapter(docListAdapter);



    }

    public ArrayList<File> getfile(File dir) {
        File listFile[] = dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (int i = 0; i < listFile.length; i++) {

                if (listFile[i].isDirectory()) {
                    //fileList.add(listFile[i]);
                    getfile(listFile[i]);

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

}
