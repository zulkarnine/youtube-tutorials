package com.zulkarnine.androidapp;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

public class GameActivity extends Activity {

	GameView gameView;
	SensorManager sensorManager;
	SensorEventListener sensorEventListener;
	Sensor accelerometerSensor;
	private static float gX,gY;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().addFlags(LayoutParams.FLAG_KEEP_SCREEN_ON);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		gameView=new GameView(this);
		initializeSensors();
		
		setContentView(gameView);
	}

	

	public static float getgX() {
		return gX;
	}

	public static float getgY() {
		return gY;
	}



	private void initializeSensors() {
		sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
		sensorEventListener=new SensorEventListener() {
			
			@Override
			public void onSensorChanged(SensorEvent event) {
				if (event.sensor.getType()==Sensor.TYPE_ACCELEROMETER) {
					gX=-event.values[0];
					gY=event.values[1];
					
				}
			}
			
			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {
				
			}
		};
		accelerometerSensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		
		startUsingSensors();
		
	}

	
	private void startUsingSensors() {
		sensorManager.registerListener(sensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	private void stopUsingSensors() {
		sensorManager.unregisterListener(sensorEventListener);
	}



	@Override
	protected void onPause() {
		stopUsingSensors();
		super.onPause();
	}
	
	@Override
	protected void onResume() {
		startUsingSensors();
		super.onResume();
	}
	
	@Override
	protected void onStop() {
		stopUsingSensors();
		super.onStop();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

}
