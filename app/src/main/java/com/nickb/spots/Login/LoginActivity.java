package com.nickb.spots.Login;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.nickb.spots.R;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    private RelativeLayout mLoadingLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d(TAG, "onCreate: Started");


        mLoadingLayout = findViewById(R.id.RelLayoutLoading);
        mLoadingLayout.setVisibility(View.GONE);
    }
}
