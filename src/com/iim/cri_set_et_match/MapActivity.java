package com.iim.cri_set_et_match;

import java.util.Calendar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class MapActivity extends Activity implements LocationListener{

	private GoogleMap maps;
	private MapFragment mapFragment;
	private String string = null;

	private String[] data;
	private String[] geo;

	private LocationManager locationManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().hide();
		setContentView(R.layout.activity_map);

		mapFragment = (MapFragment) this.getFragmentManager().findFragmentById(R.id.map);
		maps = mapFragment.getMap();

		// modification settings map

		UiSettings settings = maps.getUiSettings();
		settings.setZoomControlsEnabled(true);
		settings.setRotateGesturesEnabled(true);
		settings.setScrollGesturesEnabled(true);
		settings.setTiltGesturesEnabled(true);

		// Marqueur
		MarkerOptions options = new MarkerOptions();
		Calendar mc = Calendar.getInstance();
		int day = mc.get(Calendar.DAY_OF_YEAR);

		if(day<120){
			//Australia
			string = getResources().getString(R.string.mp);
		}else if(day < 160){
			// Roland Garros
			string = getResources().getString(R.string.rg);
		}else if(day < 200){
			//Wimbledon
			string = getResources().getString(R.string.wm);
		}else if(day < 263){
			// USA
			string = getResources().getString(R.string.fm);
		}else{
			// Else
			string = getResources().getString(R.string.rg);
		}

		data = string.split(";");
		geo = data[2].split(",");

		LatLng court = new LatLng(Float.parseFloat(geo[0]), Float.parseFloat(geo[1]));
		options.position(court);

		options.title(data[0]);
		options.snippet(data[1]);
		options.draggable(true);
		Marker lieu = maps.addMarker(options);

		//My Location
		locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
		if(locationManager.isProviderEnabled(locationManager.GPS_PROVIDER)){
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, this);
			locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 0, this);
		}

		// Zoom sur le point
		maps.moveCamera(CameraUpdateFactory.newLatLngZoom(lieu.getPosition(),17));

		// changement type carte
		maps.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

		maps.animateCamera(CameraUpdateFactory.newLatLngZoom(lieu.getPosition(),17), 2000, null);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Stub de la méthode généré automatiquement
		// Distance
        Location destination = new Location("Destination");
        destination.setLatitude(Float.parseFloat(geo[0]));
        destination.setLongitude(Float.parseFloat(geo[1]));
        
		if(location.distanceTo(destination) < 20000){
        	maps.setMyLocationEnabled(true);
        }
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Stub de la méthode généré automatiquement

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Stub de la méthode généré automatiquement

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Stub de la méthode généré automatiquement

	}

}