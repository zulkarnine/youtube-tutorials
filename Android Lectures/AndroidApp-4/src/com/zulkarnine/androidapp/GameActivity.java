package com.zulkarnine.androidapp;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.Toast;

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
		
//		gameView=new GameView(this);
		
		initializeSensors();
		
		setContentView(R.layout.activity_game);
		gameView=(GameView) findViewById(R.id.myGameView);
		
		initializeButtons();
		
	}

	

	private void initializeButtons() {
		final Button moveLeftButton=(Button) findViewById(R.id.leftButton);
		
		moveLeftButton.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					gameView.drawingThread.dock.startMovingLeft();
					moveLeftButton.getBackground().setAlpha(100);
					break;

				case MotionEvent.ACTION_UP:
					gameView.drawingThread.dock.stopMovingLeft();
					moveLeftButton.getBackground().setAlpha(255);
					break;

				default:
					break;
				}
				
				return false;
			}
		});

		
		
		final Button moveRightButton=(Button) findViewById(R.id.rightButton);
		
		moveRightButton.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					gameView.drawingThread.dock.startMovingRight();
					moveRightButton.getBackground().setAlpha(100);
					break;

				case MotionEvent.ACTION_UP:
					gameView.drawingThread.dock.stopMovingRight();
					moveRightButton.getBackground().setAlpha(255);
					break;

				default:
					break;
				}

				return false;
			}
		});
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
					
					if (gY<0) {
						stopUsingSensors();
						gameView.drawingThread.animationThread.stopThread();
						gameView.drawingThread.scoreCounterThread.stopThread();
						
						AlertDialog.Builder alertBuilder= new AlertDialog.Builder(GameActivity.this);
						alertBuilder.setTitle("No Cheating!!");
						alertBuilder.setIcon(R.drawable.warning);
						alertBuilder.setMessage("You are shaking or holding your phone upside down!!");
						
						alertBuilder.setPositiveButton("RESTART", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								restartGame(null);
							}
						});
						
						alertBuilder.setNegativeButton("QUIT", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								stopGame(null);
								
								
							}
						});
						
						alertBuilder.show();
						
						
					}
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
	
	public void pauseGame(View view) {
		if (gameView.drawingThread.pauseFlag==false) {
			gameView.drawingThread.animationThread.stopThread();
			gameView.drawingThread.pauseFlag=true;		
			view.setBackgroundResource(R.drawable.unlock);
			
		}else {
			gameView.drawingThread.animationThread=new AnimationThread(gameView.drawingThread);
			gameView.drawingThread.animationThread.start();
			view.setBackgroundResource(R.drawable.lock);
			gameView.drawingThread.pauseFlag=false;
			
		}
	}
	
	public void restartGame(View view) {
		stopUsingSensors();
		startUsingSensors();
		
		gameView.drawingThread.stopThread();
		gameView.drawingThread=new DrawingThread(gameView, this);
		gameView.drawingThread.start();
		
		Toast.makeText(this, "Game Restarted", Toast.LENGTH_SHORT).show();
	}
	
	public void stopGame(View view) {
		this.finish();
	}
	

}
