package com.zulkarninecsedictionary;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DictionaryListActivity extends Activity {

	TextView userTextView;
	EditText searchEditText;
	Button searchButton;
	ListView dictionaryListView;
	
	String logTagString="DICTIONARY";
	ArrayList<WordDefinition> allWordDefinitions=new ArrayList<WordDefinition>();
	
	
	DictionaryDatabaseHelper myDictionaryDatabaseHelper;
	SharedPreferences sharedPreferences;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dictionary_list);
		
		Log.d("DICTIONARY", "second activity started");
		
		
		userTextView=(TextView) findViewById(R.id.personTextView);
		userTextView.setText(getIntent().getStringExtra(MainActivity.USER_NAME_STRING)+"'s Dictionary");
		
		searchEditText=(EditText) findViewById(R.id.searchEditText);
		searchButton=(Button) findViewById(R.id.searchButton);
		dictionaryListView=(ListView) findViewById(R.id.dictionaryListView);
		
		myDictionaryDatabaseHelper=new DictionaryDatabaseHelper(this, "Dictionary", null, 1);
		sharedPreferences=getSharedPreferences(MainActivity.SHARED_NAME_STRING, MODE_PRIVATE);
		
		
		boolean initialized=sharedPreferences.getBoolean("initialized", false);
		
		if (initialized==false) {
			//Log.d(logTagString, "initializing for the first time");
			initializeDatabase();
			SharedPreferences.Editor editor=sharedPreferences.edit();
			editor.putBoolean("initialized", true);
			editor.commit();
			
		}else {
			Log.d(logTagString, "db already initialized");
		}
		
		allWordDefinitions=myDictionaryDatabaseHelper.getAllWords();
		
		dictionaryListView.setAdapter(new BaseAdapter() {
			
			@Override
			public View getView(int position, View view, ViewGroup arg2) {
				if (view==null) {
					view=getLayoutInflater().inflate(R.layout.list_item, null);
				}
				TextView textView=(TextView) view.findViewById(R.id.listItemTextView);
				textView.setText(allWordDefinitions.get(position).word);
				
				return view;
			}
			
			@Override
			public long getItemId(int arg0) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public Object getItem(int arg0) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return allWordDefinitions.size();
			}
		});
		
		dictionaryListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long arg3) {
				Intent intent =new Intent(DictionaryListActivity.this, WordDefinitionDetailActivity.class);
				intent.putExtra("word", allWordDefinitions.get(position).word);
				intent.putExtra("definition", allWordDefinitions.get(position).definition);
				
				startActivity(intent);
			}
		});
		
		searchButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String string=searchEditText.getText().toString();
				
				WordDefinition wordDefinition=myDictionaryDatabaseHelper.getWordDefinition(string);
				
				if (wordDefinition==null) {
					Toast.makeText(DictionaryListActivity.this, "Word not found", Toast.LENGTH_LONG).show();
				}else {
					Intent intent =new Intent(DictionaryListActivity.this, WordDefinitionDetailActivity.class);
					intent.putExtra("word", wordDefinition.word);
					intent.putExtra("definition", wordDefinition.definition);
					
					startActivity(intent);
				}
				
				
			}
		});
		
		
		
		
	}

	//
	
	
	private void initializeDatabase() {
		InputStream inputStream=getResources().openRawResource(R.raw.dictionary);
		BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
		DictionaryLoader.loadData(bufferedReader, myDictionaryDatabaseHelper);
				
	}

	

}
