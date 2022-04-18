package com.zulkarnine.androidapp;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Paint.Align;
import android.view.Display;
import android.view.WindowManager;

public class DrawingThread extends Thread {
	
	private Canvas canvas;
	GameView gameView;
	Context context;
	
	boolean threadFlag=false;
	boolean touchedFlag=false;
	boolean pauseFlag=false;
	
	
	Bitmap backgroundBitmap;
	int displayX,displayY;
	
	int maxScore=0;
	
	ArrayList<Robot> allRobots;
	ArrayList<Bitmap> allPossibleRobots;
	
	AnimationThread animationThread;
	ScoreCounterThread scoreCounterThread;
	
	Paint scorePaint;
	
	Dock dock;
	
	public DrawingThread( GameView gameView, Context context) {
		super();
		this.gameView = gameView;
		this.context = context;
	
		initializeAll();
		
	
	}


	private void initializeAll() {
		WindowManager windowManager=(WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display defaultDisplay=windowManager.getDefaultDisplay();
		
		Point displayDimension=new Point();
		defaultDisplay.getSize(displayDimension);
		
		displayX=displayDimension.x;
		displayY=displayDimension.y;
		
		backgroundBitmap=BitmapFactory.decodeResource(context.getResources(), R.drawable.background);
		backgroundBitmap=Bitmap.createScaledBitmap(backgroundBitmap, displayX, displayY, true);
		
		initializeAllPossibleRobots();
		scoreCounterThread=new ScoreCounterThread(this);
		
		dock=new Dock(this, R.drawable.dock);
		scorePaint=new Paint();
		scorePaint.setColor(Color.WHITE);
		scorePaint.setTextAlign(Align.CENTER);
		scorePaint.setTextSize(displayX/10);
		
	
	}


	private void initializeAllPossibleRobots() {
		allRobots=new ArrayList<Robot>();
		allPossibleRobots=new ArrayList<Bitmap>();
		
		allPossibleRobots.add(giveResizedRobotBitmap(R.drawable.robot1));		
		allPossibleRobots.add(giveResizedRobotBitmap(R.drawable.robot2));
		allPossibleRobots.add(giveResizedRobotBitmap(R.drawable.robot3));
		allPossibleRobots.add(giveResizedRobotBitmap(R.drawable.robot4));
		allPossibleRobots.add(giveResizedRobotBitmap(R.drawable.robot5));
		
		
		
	}
	
	private Bitmap giveResizedRobotBitmap(int resourceID) {
		Bitmap tempBitmap=BitmapFactory.decodeResource(context.getResources(), resourceID);
		tempBitmap=Bitmap.createScaledBitmap(tempBitmap, displayX/5, (tempBitmap.getHeight()/tempBitmap.getWidth())*(displayX/5), true);
	
		return tempBitmap;
	}


	@Override
	public void run() {
		threadFlag=true;
		animationThread=new AnimationThread(this);
		animationThread.start();
		scoreCounterThread.start();
		
		while (threadFlag) {
			
			canvas=gameView.surfaceHolder.lockCanvas();
			
			try {
				synchronized (gameView.surfaceHolder) {
					
					updateDisplay();
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				if (canvas!=null) {
					gameView.surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}
			
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
		}
		
		scoreCounterThread.stopThread();
		animationThread.stopThread();
		
	}
	
	
	private void updateDisplay() {
		canvas.drawBitmap(backgroundBitmap, 0	, 0	, null);
		drawDock();
		
		for (int i = 0; i < allRobots.size(); i++) {
			Robot tempRobot=allRobots.get(i);
			
			canvas.drawBitmap(tempRobot.robotBitmap, tempRobot.centerX-(tempRobot.width/2), tempRobot.centerY-(tempRobot.height/2), tempRobot.robotPaint);
		}
		
		if (pauseFlag) {
			pauseStateDraw();
		}
		
		drawScore();
		
		//drawSensorData();
	}


	private void pauseStateDraw() {
		Paint paint=new Paint();
		paint.setColor(Color.WHITE);
		paint.setTextSize(100);
		paint.setAlpha(170);
		paint.setTextAlign(Align.CENTER);
		canvas.drawARGB(170, 0, 0, 0);
		canvas.drawText("PAUSED",displayX/2, displayY/2, paint);
		
	}


	public void stopThread() {
		threadFlag=false;
	}
	
	private void drawSensorData() {
		Paint paint=new Paint();
		paint.setColor(Color.GREEN);
		paint.setTextSize(displayX/10);
		
		
		canvas.drawText("X-Axis :"+GameActivity.getgX(), 0, displayY/3, paint);
		canvas.drawText("Y-Axis :"+GameActivity.getgY(), 0, displayY/3+(displayX/5), paint);
	}
	
	private void drawDock() {
		canvas.drawBitmap(dock.dockBitmap, dock.topleftPoint.x, dock.topleftPoint.y, null);
	}
	
	private void drawScore() {
		if (maxScore>1000) {
			scorePaint.setColor(Color.GREEN);
			
			if (maxScore>10000) {
				
				scorePaint.setColor(Color.CYAN);
				if (maxScore>100000) {
					scorePaint.setColor(Color.RED);
				}
			}
		}
		
		canvas.drawText("Score : "+maxScore, displayX/2, displayY/7 , scorePaint);
	}
	
	
}
