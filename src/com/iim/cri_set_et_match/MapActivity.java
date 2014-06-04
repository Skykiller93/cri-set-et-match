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
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class MapActivity extends Activity {
	
	private GoogleMap maps;
	private MapFragment mapFragment;
	private String string = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
			// Rolang Garos
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
		
		String[] data = string.split(";");
		String[] geo = data[2].split(",");
		options.position(new LatLng(Float.parseFloat(geo[0]), Float.parseFloat(geo[1])));
		options.title(data[0]);
		options.snippet(data[1]);
		Marker lieu = maps.addMarker(options);
		
		options.draggable(true);
		
		// Zoom sur le point
		maps.moveCamera(CameraUpdateFactory.newLatLngZoom(lieu.getPosition(),17));
		
		// changement type carte
		maps.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
		// Localisation de l'user
		maps.setMyLocationEnabled(true);
		
		maps.animateCamera(CameraUpdateFactory.newLatLngZoom(lieu.getPosition(),17), 2000, null);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}