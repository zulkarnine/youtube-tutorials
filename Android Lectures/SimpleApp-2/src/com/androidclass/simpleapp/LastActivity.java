package com.androidclass.simpleapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class LastActivity extends Activity {

	TextView finalTextView;
	Button goToWishListButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_last);
		
		
		finalTextView=(TextView) findViewById(R.id.finalTextView);
		goToWishListButton=(Button) findViewById(R.id.goToWishListButton);
		
		goToWishListButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(LastActivity.this, WishListActivity.class);
				startActivity(intent);
				
			}
		});
		
		
		finalTextView.setText("WELCOME!!\n"+getIntent().getStringExtra("PERSON_NAME"));
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.last, menu);
		return true;
	}

}
