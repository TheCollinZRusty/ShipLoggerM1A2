package org.wit.shiploggerm1a2.activities

import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.support.annotation.NonNull
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.tomtom.online.sdk.common.location.LatLng

import com.tomtom.online.sdk.map.*

import org.wit.shiploggerm1a2.R

class MapActivity : AppCompatActivity(), OnMapReadyCallback {


    override fun onMapReady(@NonNull tomtomMap: TomtomMap) {
        this.map = tomtomMap
        setUpMap()
    }
    private lateinit var map: TomtomMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapFragment) as MapFragment
        mapFragment.getAsyncMap(this)
    }


    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this,
//                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }
        map.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(this) {
                location ->
            if (location != null){
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                val balloon = SimpleMarkerBalloon("You are Here")
                map.addMarker(MarkerBuilder(currentLatLng).markerBalloon(balloon))
                map.centerOn(CameraPosition.builder(currentLatLng).zoom(7.0).build())
            }

        }
    }
}



//Referencing

//http://carolbolgersoftwaredeveloper.blogspot.com/2018/10/getting-started-with-tomtom-maps-sdk.html

//TomTom Maps API

//TODO: Trying and solve issue involving LOCATION_PERMISSION_REQUEST_CODE not compiling



