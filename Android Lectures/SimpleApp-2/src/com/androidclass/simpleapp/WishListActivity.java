package com.androidclass.simpleapp;

import java.util.ArrayList;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.hardware.input.InputManager;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class WishListActivity extends Activity {

	EditText wishEditText;
	Button addWishButton;
	ListView wishListView;
	
	ArrayList<Item> arrayList;
	BaseAdapter adapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wish_list);
		initializeAll();
		
		
	}
	
	private void initializeAll() {
		wishEditText=(EditText) findViewById(R.id.newWishEditText);
		addWishButton=(Button) findViewById(R.id.addWishButton);
		wishListView=(ListView) findViewById(R.id.wishListView);
		arrayList=new ArrayList<Item>();
		adapter=new BaseAdapter() {
			
			LayoutInflater inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			@Override
			public View getView(int position, View view, ViewGroup viewGroup) {
				if (view==null) {
					view=inflater.inflate(R.layout.wish_list_item, null);
				}
				TextView wishTextView=(TextView) view.findViewById(R.id.wishlistItemTextView);
				TextView dateTextView=(TextView) view.findViewById(R.id.wishlistDateTextView);
				
				wishTextView.setText(arrayList.get(position).getWishString());
				Date date=arrayList.get(position).getDate();
				dateTextView.setText(DateFormat.format("dd/MM/yyyy HH:mm:ss a", date));
				
				return view;
			}
			
			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public Object getItem(int position) {
				
				return arrayList.get(position);
			}
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return arrayList.size();
			}
		};
		
		wishListView.setAdapter(adapter);
		
		addWishButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String string=wishEditText.getText().toString();
				Date date=new Date();
				Item item=new Item(string, date);
				arrayList.add(item);

				adapter.notifyDataSetChanged();
				wishEditText.setText("");
				InputMethodManager inputMethodManager=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				inputMethodManager.hideSoftInputFromWindow(wishEditText.getWindowToken(), 0);
				
				Toast.makeText(getBaseContext(), "New Wish Added To List", Toast.LENGTH_SHORT).show();
			}
		});
		
		wishListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View view,
					int position, long ID) {
				arrayList.remove(position);
				adapter.notifyDataSetChanged();
				
				Toast.makeText(getBaseContext(), "One Item Removed", Toast.LENGTH_SHORT).show();
				
				return false;
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wish_list, menu);
		return true;
	}

}
