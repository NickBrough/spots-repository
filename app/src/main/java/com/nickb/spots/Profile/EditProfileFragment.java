package com.nickb.spots.Profile;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nickb.spots.R;
import com.nickb.spots.Utils.UniversalImageLoader;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.zip.Inflater;

public class EditProfileFragment extends Fragment {

    private static final String TAG = "EditProfileFragment";

    private ImageView mProfilePhoto;
    private ImageView mBackArrow;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_editprofile, container, false);
        mProfilePhoto = (ImageView) view.findViewById(R.id.profile_photo);
        mBackArrow = (ImageView) view.findViewById(R.id.backArrow);


        setProfileImage();

        setupBackarrow();

        return view;
    }



    private void setupBackarrow() {

        mBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: navigating back to account settings");

                getActivity().finish();
            }
        });
    }


    
    
    private void setProfileImage() {
        Log.d(TAG, "setProfileImage: setting profile image");
        String imgURL = "https://i7.pngguru.com/preview/987/118/380/computer-icons-login-user-profile-others-thumbnail.jpg";
        UniversalImageLoader.setImage(imgURL, mProfilePhoto, null, "");
    }


}
