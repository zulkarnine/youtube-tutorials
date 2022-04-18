package com.androidclass.simpleapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class LastActivity extends Activity {

	TextView finalTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_last);
		
		
		finalTextView=(TextView) findViewById(R.id.finalTextView);
		
		finalTextView.setText("WELCOME!!\n"+getIntent().getStringExtra("PERSON_NAME"));
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.last, menu);
		return true;
	}

}
