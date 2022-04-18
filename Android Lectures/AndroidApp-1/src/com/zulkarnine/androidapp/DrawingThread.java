package com.zulkarnine.androidapp;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

public class DrawingThread extends Thread {
	
	private Canvas canvas;
	private GameView gameView;
	private Context context;
	
	boolean threadFlag=false;
	
	Bitmap backgroundBitmap;
	int displayX,displayY;
	
	ArrayList<Robot> allRobots;
	ArrayList<Bitmap> allPossibleRobots;
	
	
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
				Thread.sleep(17);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
		}
		
	}
	
	
	private void updateDisplay() {
		canvas.drawBitmap(backgroundBitmap, 0	, 0	, null);
		for (int i = 0; i < allRobots.size(); i++) {
			Robot tempRobot=allRobots.get(i);
			
			canvas.drawBitmap(tempRobot.robotBitmap, tempRobot.centerX-(tempRobot.width/2), tempRobot.centerY-(tempRobot.height/2), tempRobot.robotPaint);
		}
	}


	public void stopThread() {
		threadFlag=false;
	}
	
	
}
