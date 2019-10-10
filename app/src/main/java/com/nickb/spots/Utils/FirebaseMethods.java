package com.nickb.spots.Utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nickb.spots.R;
import com.nickb.spots.models.User;
import com.nickb.spots.models.UserAccountSettings;
import com.nickb.spots.models.UserSettings;

import androidx.annotation.NonNull;

public class FirebaseMethods {
    private static final String TAG = "FirebaseMethods";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;

    private String userID;

    private Context mContext;

    public FirebaseMethods(Context context) {
        mContext = context;
        mAuth = FirebaseAuth.getInstance();

       mFirebaseDatabase = FirebaseDatabase.getInstance();
       myRef = mFirebaseDatabase.getReference();

        if(mAuth.getCurrentUser() != null) {
            userID = mAuth.getUid();
        }

    }

    public void updateUsername(String username) {

        myRef.child(mContext.getString(R.string.dbname_user_account_settings))
                .child(userID)
                .child(mContext.getString(R.string.field_username))
                .setValue(username);

        myRef.child(mContext.getString(R.string.dbname_user))
                .child(userID)
                .child(mContext.getString(R.string.field_username))
                .setValue(username);

    }

    public void updateHomeCity(String homeCity) {
        myRef.child(mContext.getString(R.string.dbname_user_account_settings))
                .child(userID)
                .child(mContext.getString(R.string.field_home_city))
                .setValue(homeCity);
    }

    public void updateHomeTown(String homeTown) {
        myRef.child(mContext.getString(R.string.dbname_user_account_settings))
                .child(userID)
                .child(mContext.getString(R.string.field_home_town))
                .setValue(homeTown);
    }

    public void updateDisplayName(String displayName) {
        myRef.child(mContext.getString(R.string.dbname_user_account_settings))
                .child(userID)
                .child(mContext.getString(R.string.field_display_name))
                .setValue(displayName);
    }

    public void updateDescription(String description) {
        myRef.child(mContext.getString(R.string.dbname_user_account_settings))
                .child(userID)
                .child(mContext.getString(R.string.field_description))
                .setValue(description);
    }


    /**
     * Register a new user to firebase
     * @param email
     * @param password
     * @param username
     */
    public void registerNewEmail(final String email, String password, final String username) {
        Log.d(TAG, "registerNewEmail: attempting to register new email");

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(mContext, "Authentication success.", Toast.LENGTH_SHORT).show();

                            FirebaseUser user = mAuth.getCurrentUser();
                            userID = user.getUid();

                            // send verification email - commented out for now
                             sendVerificationEmail(user);


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Log.d(TAG, "onComplete: AuthState changed");

                            Toast.makeText(mContext, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }


    public void sendVerificationEmail(FirebaseUser user) {
        if(user != null) {
            user.sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(!task.isSuccessful()) {
                                Toast.makeText(mContext, "Unable to send verification email", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }


    /**
     * Adds information to the user_account_settings node
     * Adds information to the user node
     *
     * @param email
     * @param username
     * @param description
     * @param profile_photo
     */
    public void addNewUser(String email, String username, String description, String profile_photo) {
        // create user object
        User user = new User(email, StringManipulation.condenseUsername(username), userID);

        // create user account settings object
        UserAccountSettings userAccountSettings = new UserAccountSettings(
                description,
                username,
                0,
                0,
                "",
                "",
                StringManipulation.condenseUsername(username),
                profile_photo);

        // find that node in the database and set it to the user
        myRef.child(mContext.getString(R.string.dbname_user))
                .child(userID)
                .setValue(user);

        myRef.child(mContext.getString(R.string.dbname_user_account_settings))
                .child(userID)
                .setValue(userAccountSettings);

    }


    /**
     * Returns the user and user_account_settings information from the database
     * @param dataSnapshot
     * @return
     */
    public UserSettings getUserSettings(DataSnapshot dataSnapshot) {
        Log.d(TAG, "getUserAccountSettings: retrieving user account settings from firebase");

        UserAccountSettings settings = new UserAccountSettings();
        User user = new User();

        for(DataSnapshot ds: dataSnapshot.getChildren()) {
            // retrieve UserAccountSettings
            if (ds.getKey().equals(mContext.getString(R.string.dbname_user_account_settings))) {
                    Log.d(TAG, "getUserSettings: dataSnapshot: " + ds);

                    try {
                        settings.setDisplay_name(
                                ds.child(userID)
                                        .getValue(UserAccountSettings.class)
                                        .getDisplay_name()
                        );

                        settings.setUsername(
                                ds.child(userID)
                                        .getValue(UserAccountSettings.class)
                                        .getUsername()
                        );

                        settings.setDescription(
                                ds.child(userID)
                                        .getValue(UserAccountSettings.class)
                                        .getDescription()
                        );

                        settings.setHome_city(
                                ds.child(userID)
                                        .getValue(UserAccountSettings.class)
                                        .getHome_city()
                        );

                        settings.setHome_town(
                                ds.child(userID)
                                        .getValue(UserAccountSettings.class)
                                        .getHome_town()
                        );

                        settings.setRating(
                                ds.child(userID)
                                        .getValue(UserAccountSettings.class)
                                        .getRating()
                        );

                        settings.setSpot_count(
                                ds.child(userID)
                                        .getValue(UserAccountSettings.class)
                                        .getSpot_count()
                        );

                        settings.setProfile_photo(
                                ds.child(userID)
                                        .getValue(UserAccountSettings.class)
                                        .getProfile_photo()
                        );
                        Log.d(TAG, "getUserSettings: retrieved user account settings information: " + settings.toString());
                    } catch (NullPointerException e) {
                        Log.e(TAG, "getUserSettings: Null pointer exception: " + e.getMessage() );
                    }
            }

            if (ds.getKey().equals(mContext.getString(R.string.dbname_user))) {
                Log.d(TAG, "getUserSettings: dataSnapshot: " + ds);

                try {
                    user.setUsername(
                            ds.child(userID)
                                    .getValue(User.class)
                                    .getUsername()
                    );

                    user.setEmail(
                            ds.child(userID)
                                    .getValue(User.class)
                                    .getEmail()
                    );

                    user.setUser_id(
                            ds.child(userID)
                                    .getValue(User.class)
                                    .getUser_id()
                    );
                    Log.d(TAG, "getUserSettings: retrieved user information: " + user.toString());
                } catch (NullPointerException e) {
                    Log.e(TAG, "getUserSettings: Null pointer exception: " + e.getMessage() );
                }

            }
        }

        return new UserSettings(user, settings);

    }

}
