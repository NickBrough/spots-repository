package com.nickb.spots.Home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.nickb.spots.R;


public class MapViewFragment extends Fragment {

    private static final String TAG = "MapViewFragment";
    MapView mMapView;
    private GoogleMap googleMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_map_view, container, false);


        mMapView = (MapView) view.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately


        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {

            Log.d(TAG, "onCreateView: permissions granted");

            mMapView.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap mMap) {
                    Log.d(TAG, "onMapReady: initialising map");


                    googleMap = mMap;



                    // For showing a move to my location button
                    googleMap.setMyLocationEnabled(true);

                    // For dropping a marker at a point on the Map
                    LatLng sydney = new LatLng(-34, 151);
                    googleMap.addMarker(new MarkerOptions()
                            .position(sydney)
                            .title("Marker Title"));


                    // For zooming automatically to the location of the marker
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }
            });
        } else {
            Log.d(TAG, "onCreateView: permissions not granted");
        }




        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }


}









//
//
//    private void getLocationPermission() {
//        /*
//         * Request location permission, so that we can get the location of the
//         * device. The result of the permission request is handled by a callback,
//         * onRequestPermissionsResult.
//         */
//        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
//                android.Manifest.permission.ACCESS_FINE_LOCATION)
//                == PackageManager.PERMISSION_GRANTED) {
//            mLocationPermissionGranted = true;
//            getChatrooms();
//            getUserDetails();
//        } else {
//            ActivityCompat.requestPermissions(this,
//                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
//                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
//        }
//    }
//
//
//    private void getUserDetails(){
//        if(mUserLocation == null){
//            mUserLocation = new UserLocation();
//            DocumentReference userRef = mDb.collection(getString(R.string.collection_users))
//                    .document(FirebaseAuth.getInstance().getUid());
//
//            userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                    if(task.isSuccessful()){
//                        Log.d(TAG, "onComplete: successfully set the user client.");
//                        User user = task.getResult().toObject(User.class);
//                        mUserLocation.setUser(user);
//                        getLastKnownLocation();
//                    }
//                }
//            });
//        }
//        else {
//            getLastKnownLocation();
//        }
//    }
//
//    private void getLastKnownLocation() {
//        Log.d(TAG, "getLastKnownLocation: called.");
//
//
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            return;
//        }
//        mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<android.location.Location>() {
//            @Override
//            public void onComplete(@NonNull Task<android.location.Location> task) {
//                if (task.isSuccessful()) {
//                    Location location = task.getResult();
//                    GeoPoint geoPoint = new GeoPoint(location.getLatitude(), location.getLongitude());
//                    mUserLocation.setGeo_point(geoPoint);
//                    mUserLocation.setTimestamp(null);
//                    saveUserLocation();
//                }
//            }
//        });
//
//    }
//
//    private void saveUserLocation(){
//
//        if(mUserLocation != null){
//            DocumentReference locationRef = mDb
//                    .collection(getString(R.string.collection_user_locations))
//                    .document(FirebaseAuth.getInstance().getUid());
//
//            locationRef.set(mUserLocation).addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//                    if(task.isSuccessful()){
//                        Log.d(TAG, "saveUserLocation: \ninserted user location into database." +
//                                "\n latitude: " + mUserLocation.getGeo_point().getLatitude() +
//                                "\n longitude: " + mUserLocation.getGeo_point().getLongitude());
//                    }
//                }
//            });
//        }
//    }
//
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        Log.d(TAG, "onActivityResult: called.");
//        switch (requestCode) {
//            case PERMISSIONS_REQUEST_ENABLE_GPS: {
//                if(mLocationPermissionGranted){
//                    getChatrooms();
//                    getUserDetails();
//                }
//                else{
//                    getLocationPermission();
//                }
//            }
//        }
//
//    }



