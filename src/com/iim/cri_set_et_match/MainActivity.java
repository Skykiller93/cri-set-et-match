package com.iim.cri_set_et_match;

import java.util.Calendar;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;


public class MainActivity extends Activity implements OnClickListener, android.view.View.OnClickListener {
    
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout layout =  (RelativeLayout) findViewById(R.id.main);
        layout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent call = new Intent(com.iim.cri_set_et_match.MainActivity.this, com.iim.cri_set_et_match.JeuActivity.class);
        startActivity(call);
    }

	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Stub de la méthode généré automatiquement
		
	}
}