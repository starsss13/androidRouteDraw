package com.example.user.sqlite;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback {
    Location myloc;
    private GoogleMap mMap;
    private final static int REQUEST_lOCATION = 90;
    EditText tvdb;
    veritabani db;

    List<Address> addresses = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent intent = getIntent();
        // FirebaseDatabase.getInstance().setPersistenceEnabled(true);                    internetde degilken ki bilgileri getirir
        tvdb=(EditText) findViewById(R.id.tvdb);
        Context context=this;
        db=new veritabani(context);


        ZoomControls zoom = (ZoomControls) findViewById(R.id.zoom);                   //zoom componenti gecin geçerli
        zoom.setOnZoomOutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.animateCamera(CameraUpdateFactory.zoomOut());
            }
        });
        zoom.setOnZoomInClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.animateCamera(CameraUpdateFactory.zoomIn());
            }
        });

        final Button dbcikar=(Button)findViewById(R.id.dbcikar);
        dbcikar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location=String.valueOf(tvdb.getText());
                if(location!=null){
                    db.alanSilme(location);
mMap.clear();
                    ekranaHaritalariGetir();

                }

            }
        });




                }









        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_lOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    mMap.setMyLocationEnabled(true);
                }
            } else {
                Toast.makeText(getApplicationContext(), "Kullanıcı konum iznini vermedi", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Toast.makeText(getApplicationContext(), "çıkarmak istedigin yere bas", Toast.LENGTH_LONG).show();
        ekranaHaritalariGetir();
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {


                String name = marker.getTitle();
                db.alanSilme(name);
                mMap.clear();
                ekranaHaritalariGetir();
                return false;
            }
        });


    }



    private void ekranaHaritalariGetir() {
        LatLng latLngg = new LatLng(0, 0);
        List<harita> harita= db.alanlariListele(); //veri tabnı silme işlemleri
        for (harita haritam:harita){
            LatLng latLng=new LatLng(haritam.getLatitude(),haritam.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title(haritam.getLocation()));
            Marker melbourne = mMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));




}}}