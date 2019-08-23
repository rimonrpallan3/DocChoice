package com.glen.filetest.activity.Landing.fragment.DOCFragment;

import android.Manifest;
import android.content.Intent;
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
import com.glen.filetest.activity.Landing.fragment.DOCFragment.view.IDOCFrgView;
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

public class DOCFragment extends Fragment implements IDOCFrgView , EasyPermissions.PermissionCallbacks{
    RecyclerView rvDOCList;
    DocListAdapter docListAdapter;
    private File root;
    private ArrayList<File> fileList = new ArrayList<File>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.fragment_doc, container, false);
        rvDOCList = rootView.findViewById(R.id.rvDOCList);
        loadDOCFiles();
        return rootView;
    }

    @AfterPermissionGranted(RC_STORAGE_PERM)
    void loadDOCFiles() {
        System.out.println("DOCFragment loadDOCFiles");
        if (EasyPermissions.hasPermissions(getContext(), StoragePerms2)) {
            // Have permission, do the thing!
                //Snackbar.make(findViewById(android.R.id.content), getResources().getString(R.string.snack_error_network_available), Snackbar.LENGTH_SHORT).show();
            root = new File(Environment.getExternalStorageState());
            fileList = Constants.getDOCfile(root);
            Gson gson = new Gson();
            String jsonString = gson.toJson(fileList);
            System.out.println("loadDOCFiles jsonString : "+jsonString);
            docListAdapter = new DocListAdapter(fileList);
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            rvDOCList.setLayoutManager(mLayoutManager);
            rvDOCList.setItemAnimator(new DefaultItemAnimator());
            rvDOCList.setAdapter(docListAdapter);

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
        fileList = Constants.getDOCfile(root);
        docListAdapter = new DocListAdapter(fileList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvDOCList.setLayoutManager(mLayoutManager);
        rvDOCList.setItemAnimator(new DefaultItemAnimator());
        rvDOCList.setAdapter(docListAdapter);
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Snackbar.make(getActivity().findViewById(android.R.id.content), getResources().getString(R.string.snack_permission_denied), Snackbar.LENGTH_SHORT).show();
    }
}
