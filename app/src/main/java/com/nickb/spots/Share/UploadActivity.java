package com.nickb.spots.Share;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nickb.spots.R;
import com.nickb.spots.Utils.FirebaseMethods;
import com.nickb.spots.models.UserSettings;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UploadActivity extends AppCompatActivity {

    private static final String TAG = "UploadActivity";

    // Fire base
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private FirebaseMethods firebaseMethods;

    // widgets
    private ImageView ivUpload, mSelectedImage;
    private EditText mTitle, mDescription;
    private Button btnUpload;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        ivUpload = findViewById(R.id.upload);
        btnUpload = findViewById(R.id.btnUpload);

        Log.d(TAG, "onCreate: got selected image: " + getIntent().getStringExtra(getString(R.string.selected_image)));


        setupFirebaseAuth();
    }


    // ----------------- Firebase ------------------




    private void setupFirebaseAuth() {
        Log.d(TAG, "setupFirebaseAuth: ");

        // Initialize Fire base Auth
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        // sets a state listener to see if user logs in or logs out
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull final FirebaseAuth firebaseAuth) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (null != user) {
                    // user is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in: " + user.getUid());
                    //onSignedInInitialize(user);
                } else {
                    // user signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out: ");
                }
            }
        };



        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Log.d(TAG, "onDataChange: database reference called");
                UserSettings userSettings = firebaseMethods.getUserSettings(dataSnapshot);
                setProfileWidget(userSettings);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

}
