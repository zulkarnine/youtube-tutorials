package com.zulkarnine.androidapp;

import android.R.integer;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Point;

public class Robot {
	int centerX,centerY;
	int height,width;
	Bitmap robotBitmap;
	Paint robotPaint;

	public Robot(Bitmap bitmap) {
		robotBitmap=bitmap;
		centerX=centerY=0;
		height=robotBitmap.getHeight();
		width=robotBitmap.getWidth();
		robotPaint=new Paint();
	}

	public Robot(Bitmap bitmap,int cX,int cY) {
		this(bitmap);
		centerX=cX;
		centerY=cY;
		
	}

	public Robot(Bitmap bitmap, Point center) {
		this(bitmap, center.x, center.y);
	}
	
	public void setCenter(Point centerPoint) {
		centerX=centerPoint.x;
		centerY=centerPoint.y;
	}
		
}