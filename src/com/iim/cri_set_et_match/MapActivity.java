package com.iim.cri_set_et_match;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class MapActivity extends Activity {
	
	private GoogleMap maps;
	private MapFragment mapFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

		mapFragment = (MapFragment) this.getFragmentManager().findFragmentById(R.id.map);
		maps = mapFragment.getMap();
		
		// modification settings map
		
		UiSettings settings = maps.getUiSettings();
		settings.setZoomControlsEnabled(false);
		settings.setCompassEnabled(false);
		settings.setMyLocationButtonEnabled(false);
		
		// pilotage des gestures
		settings.setZoomControlsEnabled(true);
		settings.setRotateGesturesEnabled(true);
		settings.setScrollGesturesEnabled(true);
		settings.setTiltGesturesEnabled(true);
		
		// Ajout d'un marqueur
		MarkerOptions options = new MarkerOptions();
		options.position(new LatLng(48.855900,2.236470));
		options.title("Pôle Universitaire");
		options.snippet("Léonard De Vinci");
		Marker lieu = maps.addMarker(options);
		
		//icone personnalisable
		// options.icon(BitmapDescriptorFactory,fromresource(R.drawable.logo.iim));
		
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