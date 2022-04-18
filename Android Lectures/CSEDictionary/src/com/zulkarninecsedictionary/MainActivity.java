package com.zulkarninecsedictionary;

import android.os.Bundle;
import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	
	final static String SHARED_NAME_STRING="sharedp";
	final static String USER_NAME_STRING="user";
	
	Button button;
	EditText editText;
	
	SharedPreferences sharedPreferences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		editText=(EditText) findViewById(R.id.userNameEditText);
		button=(Button) findViewById(R.id.enterButton);
		
		Log.d("DICTIONARY", "main activity started");
		
		
		sharedPreferences=getSharedPreferences(SHARED_NAME_STRING, MODE_PRIVATE);
		String userNameString=sharedPreferences.getString(USER_NAME_STRING, "");
		
		editText.setText(userNameString);
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String string=editText.getText().toString();
				Intent intent=new Intent(MainActivity.this, DictionaryListActivity.class);
				intent.putExtra("user", string);
				
				SharedPreferences.Editor editor=sharedPreferences.edit();
				editor.putString(USER_NAME_STRING, string);
				editor.commit();
				
				startActivity(intent);
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
