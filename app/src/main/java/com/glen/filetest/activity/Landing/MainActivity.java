package com.glen.filetest.activity.Landing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.glen.filetest.R;
import com.glen.filetest.activity.Landing.adapter.TabViewPagerAdapter;
import com.glen.filetest.activity.Landing.view.IMainView;
import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IMainView {


    private LinearLayout llView;
    private TabViewPagerAdapter adapter2;

    ViewPager vpLanding;
    TabLayout tbLanding;
    FrameLayout flTab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        llView = (LinearLayout) findViewById(R.id.llView);

        vpLanding = (ViewPager) findViewById(R.id.vpLanding);
        tbLanding = (TabLayout) findViewById(R.id.tbLanding);
        tbLanding.addTab(tbLanding.newTab().setIcon(R.drawable.pdf_icon));
        tbLanding.addTab(tbLanding.newTab().setIcon(R.drawable.doc_icon));
        //tbLanding.setSelectedTabIndicatorGravity(INDICATOR_GRAVITY_TOP);
        tbLanding.setTabGravity(TabLayout.GRAVITY_FILL);

        adapter2 = new TabViewPagerAdapter(this, getSupportFragmentManager(), tbLanding.getTabCount());
        vpLanding.setOffscreenPageLimit(2);
        vpLanding.setAdapter(adapter2);
        vpLanding.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tbLanding));



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.vpLanding);
        fragment.onActivityResult(requestCode, resultCode, data);

    }


}
