package com.tse.app.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tse.app.Adapter.Pager_Editprofile;
import com.tse.app.FilePath;
import com.tse.app.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileActivity extends AppCompatActivity implements TabLayout.BaseOnTabSelectedListener {

    private int PICK_PDF_REQUEST = 1;
    CircleImageView ivProfileImage;
    private static final String TAG = EditProfileActivity.class.getSimpleName();
    String selectedFilePath;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


    getSupportActionBar().

    setElevation(0);


    findViewByIdS();


    tabLayout =(TabLayout)

    findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().

    setText("Profile"));
        tabLayout.addTab(tabLayout.newTab().

    setText("Category"));


        for(
    int i = 0; i<tabLayout.getTabCount();i++)

    {
        if (i == 0) {
            View tab = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(i);
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
            p.setMargins(0, 0, 90, 0);
            tab.requestLayout();
        } else if (i == 1) {
            View tab = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(i);
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
            p.setMargins(100, 0, 0, 0);
            tab.requestLayout();
        }
    }


    viewPager =(ViewPager)

    findViewById(R.id.pager);

    Pager_Editprofile adapter = new Pager_Editprofile(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        tabLayout.setOnTabSelectedListener(this);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()

    {
        @Override
        public void onPageScrolled ( int i, float v, int i1){
    }

        @Override
        public void onPageSelected ( int i){
        tabLayout.getTabAt(i).select();
    }

        @Override
        public void onPageScrollStateChanged ( int i){
    }
    });

}

    private void findViewByIdS() {

        ivProfileImage = (CircleImageView) findViewById(R.id.cv_profile_image);

    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    public void backToHome(View view) {
        Intent intent = new Intent(EditProfileActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(EditProfileActivity.this, HomeActivity.class);
        startActivity(intent);
    }

}
