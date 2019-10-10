package com.nickb.spots.Profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.nickb.spots.R;
import com.nickb.spots.Utils.BottomNavigationViewHelper;
import com.nickb.spots.Utils.GridImageAdapter;
import com.nickb.spots.Utils.UniversalImageLoader;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = "ProfileActivity";
    private static final int ACTIVITY_NUM = 2;
    private static final int NUM_OF_GRID_COLUMNS = 3;

    private Context mContext = ProfileActivity.this;

    private ProgressBar mProgressBar;
    private ImageView mProfilePhoto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d(TAG, "onCreate: Started.");


        init();
//        setupActivityWidgets();
//
//        setupBottomNavigationView();
//        setProfileImage();
//        setupToolbar();

//
//        tempGridSetup();
    }


    private void init() {
        Log.d(TAG, "init: inflating fragment: " + getString(R.string.profile_fragment));

        ProfileFragment fragment = new ProfileFragment();
        FragmentTransaction transaction = ProfileActivity.this.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment); // swap out the container with the profile fragment
        transaction.addToBackStack(getString(R.string.profile_fragment)); // fragments dont keep track of their stack so you must manually do it
        transaction.commit();
    }
//
//    private void tempGridSetup() {
//        ArrayList<String> imgURLS = new ArrayList<>();
//        imgURLS.add("https://iso.500px.com/wp-content/uploads/2016/04/stock-photo-150595123.jpg");
//        imgURLS.add("https://iso.500px.com/wp-content/uploads/2016/04/stock-photo-150595123.jpg");
//        imgURLS.add("https://iso.500px.com/wp-content/uploads/2016/04/stock-photo-150595123.jpg");
//        imgURLS.add("https://iso.500px.com/wp-content/uploads/2016/04/stock-photo-150595123.jpg");
//
//        setupImageGrid(imgURLS);
//
//    }
//
//    private void setupImageGrid(ArrayList<String> imgURLs) {
//        GridView gridView = findViewById(R.id.gridView);
//
//
//        int gridWidth = getResources().getDisplayMetrics().widthPixels;
//        int imageWidth = gridWidth/NUM_OF_GRID_COLUMNS;
//
//        gridView.setColumnWidth(imageWidth);
//
//        GridImageAdapter adapter = new GridImageAdapter(mContext, R.layout.layout_grid_imageview, "", imgURLs);
//        gridView.setAdapter(adapter);
//    }
//
//    private void setProfileImage() {
//        Log.d(TAG, "setProfileImage: setting profile photo");
//        String imgURL = "cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png";
//        UniversalImageLoader.setImage(imgURL, mProfilePhoto, mProgressBar, "https://");
//    }
//
//    private void setupActivityWidgets(){
//        // temporarily stop the progress bar from spinning
//        mProgressBar = findViewById(R.id.profileProgressBar);
//        mProgressBar.setVisibility(View.GONE);
//
//        mProfilePhoto = findViewById(R.id.profile_photo);
//    }
//
//
//
//    private void setupToolbar() {
//        Toolbar toolbar = findViewById(R.id.profileToolbar);
//        setSupportActionBar(toolbar);
//
//        ImageView profileMenu = (ImageView) findViewById(R.id.profileMenu);
//
//        profileMenu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d(TAG, "onClick: navigating to account settings");
//
//                Intent intent = new Intent(mContext, AccountSettingsActivity.class);
//                startActivity(intent);
//
//            }
//        });
//    }
//
//    /**
//     * BottomNavigationView setup
//     */
//    private void setupBottomNavigationView () {
//        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
//
//        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
//        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
//        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);
//
//
//        Menu menu = bottomNavigationViewEx.getMenu();
//        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
//        menuItem.setChecked(true);
//    }


}
  