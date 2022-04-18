package com.zulkarnine.androidapp;

import android.content.res.Resources.Theme;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

public class Dock {
	
	Bitmap dockBitmap;
	int dockWidth,dockHeight;
	int leftmostpoint, rightmostPoint;
	
	Point topleftPoint=new Point(0, 0),bottomCenterPoint;
	DrawingThread drawingThread;
	
	
	boolean movingLeftFlag=false;
	boolean movingRightFlag=false;
	
	
	
	public Dock(DrawingThread drawingThread, int bitmapId) {
		this.drawingThread=drawingThread;
		Bitmap tempBitmap=BitmapFactory.decodeResource(drawingThread.context.getResources(), bitmapId);
		tempBitmap=Bitmap.createScaledBitmap(tempBitmap, drawingThread.displayX, drawingThread.displayX*tempBitmap.getHeight()/tempBitmap.getWidth(), true);
		
		dockBitmap=tempBitmap;
		dockHeight=dockBitmap.getHeight();
		dockWidth=dockBitmap.getWidth();
		
		
		
		bottomCenterPoint=new Point((int)drawingThread.displayX/2, (int)drawingThread.displayY);
		topleftPoint.y=bottomCenterPoint.y-dockHeight;
		
		updateInfo();
		
		
	}

	private void updateInfo() {
		leftmostpoint=bottomCenterPoint.x-dockWidth/2;
		rightmostPoint=bottomCenterPoint.x+dockWidth/2;

		topleftPoint.x=leftmostpoint;
	}
	
	
	public void moveDockToLeft() {
		bottomCenterPoint.x-=4;
		updateInfo();
	}
	
	public void moveDockToRight() {
		bottomCenterPoint.x+=4;
		updateInfo();
	}
	
	public void startMovingLeft() {
		Thread thread=new Thread(){
			@Override
			public void run() {
				movingLeftFlag=true;
				
				while (movingLeftFlag) {
					moveDockToLeft();				
					try {
						sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};		
		thread.start();
	}
	
	public void stopMovingLeft() {
		movingLeftFlag=false;
	}
	
	public void startMovingRight() {
		Thread thread=new Thread(){
			@Override
			public void run() {
				movingRightFlag=true;
				
				while (movingRightFlag) {
					moveDockToRight();				
					try {
						sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};		
		thread.start();
	}
	
	public void stopMovingRight() {
		movingRightFlag=false;
	}

}
