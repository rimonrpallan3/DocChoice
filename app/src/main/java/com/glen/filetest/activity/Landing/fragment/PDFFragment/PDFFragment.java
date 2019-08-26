package com.glen.filetest.activity.Landing.fragment.PDFFragment;

import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.glen.filetest.R;
import com.glen.filetest.activity.Landing.fragment.DOCFragment.adapter.DocListAdapter;
import com.glen.filetest.activity.Landing.fragment.PDFFragment.adapter.PDFListAdapter;
import com.glen.filetest.activity.Landing.fragment.PDFFragment.view.IPDFFrgView;
import com.glen.filetest.activity.common.Constants;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static com.glen.filetest.activity.common.Constants.RC_STORAGE_PERM;
import static com.glen.filetest.activity.common.Constants.StoragePerms2;

public class PDFFragment extends Fragment implements IPDFFrgView, EasyPermissions.PermissionCallbacks {
    RecyclerView rvPDFList;
    PDFListAdapter pdfListAdapter;
    private File root;
    private File root2;
    private ArrayList<File> fileList = new ArrayList<File>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pdf, container, false);
        rvPDFList = rootView.findViewById(R.id.rvPDFList);
        root = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        System.out.println("loadPDFFiles fileList Path : "+Environment.getExternalStorageDirectory().getAbsolutePath());
        System.out.println("loadPDFFiles fileList Path : "+Environment.getExternalStorageState());
        root2 = new File(Environment.getExternalStorageState());
        loadPDFFiles();
        return rootView;
    }

    @AfterPermissionGranted(RC_STORAGE_PERM)
    void loadPDFFiles() {
        System.out.println("PDFFragment loadPDFFiles");
        if (EasyPermissions.hasPermissions(getContext(), StoragePerms2)) {
            // Have permission, do the thing!
            //Snackbar.make(findViewById(android.R.id.content), getResources().getString(R.string.snack_error_network_available), Snackbar.LENGTH_SHORT).show();
            //root = new File(Environment.getExternalStorageState());
            fileList = Constants.getPDFfile(root);
            System.out.println("loadPDFFiles fileList Size : "+fileList.size());
            if(fileList.size()==0){
                fileList = Constants.getPDFfile(root2);
                System.out.println("loadPDFFiles fileList 2 Size : "+fileList.size());
            }

            Gson gson = new Gson();
            String jsonString = gson.toJson(fileList);
            System.out.println("loadPDFFiles jsonString : "+jsonString);
            pdfListAdapter = new PDFListAdapter(fileList);
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            rvPDFList.setLayoutManager(mLayoutManager);
            rvPDFList.setItemAnimator(new DefaultItemAnimator());
            rvPDFList.setAdapter(pdfListAdapter);

        } else {
            // Request one permission
            EasyPermissions.requestPermissions(this, getString(R.string.error_message_permission_storage),
                    RC_STORAGE_PERM, StoragePerms2);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        root = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath());
        fileList = Constants.getPDFfile(root);
        pdfListAdapter = new PDFListAdapter(fileList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvPDFList.setLayoutManager(mLayoutManager);
        rvPDFList.setItemAnimator(new DefaultItemAnimator());
        rvPDFList.setAdapter(pdfListAdapter);
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Snackbar.make(getActivity().findViewById(android.R.id.content), getResources().getString(R.string.snack_permission_denied), Snackbar.LENGTH_SHORT).show();
    }
}
