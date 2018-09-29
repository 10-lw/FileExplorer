package com.onezero.filemanager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.onezero.filemanager.fileContent.FilesContentFragment;
import com.onezero.filemanager.appContent.AppsContentFragment;
import com.onezero.filemanager.utils.FileScanUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener, View.OnClickListener {

    private Button backButton;
    private RadioGroup radioGroup;
    private ViewPager viewPager;
    private AppsContentFragment appsContentFragment;
    private ArrayList<Fragment> fragments;
    private FilesContentFragment filesContentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initDates();
    }

    private void initDates() {
        fragments = new ArrayList<>();
        appsContentFragment = new AppsContentFragment();
        filesContentFragment = new FilesContentFragment();


        fragments.add(appsContentFragment);
        fragments.add(filesContentFragment);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragments));
    }

    private void initViews() {
        backButton = findViewById(R.id.title_bar_back);
        viewPager = findViewById(R.id.view_pager);
        viewPager.addOnPageChangeListener(this);
        radioGroup = findViewById(R.id.title_bar_radio_group);
        radioGroup.setOnCheckedChangeListener(this);
        backButton.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.radio_button_apps:
                viewPager.setCurrentItem(0);
                break;
            case R.id.radio_button_files:
                viewPager.setCurrentItem(1);
                break;
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int position) {
        RadioButton radioButton = (RadioButton) radioGroup.getChildAt(position);
        radioButton.setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onClick(View view) {
        onBackPressed();
    }
}
