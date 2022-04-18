package com.zulkarnine.androidapp;

public class AnimationThread extends Thread {

	private boolean flag=false;
	float gravityX,gravityY;
	float timeConstant=0.3f;
	float retardationRatio=-0.7f;
	int width,height;
	int left,right,top,bottom;
	
	DrawingThread drawingThread;
	
	
	public AnimationThread(DrawingThread drawingThread) {
		super();
		this.drawingThread = drawingThread;
		updateDimensions();
	}

	private void updateDimensions() {
		width=drawingThread.allPossibleRobots.get(0).getWidth();
		height=drawingThread.allPossibleRobots.get(0).getHeight();
		
		left=width/2;
		top=height/2;
		right=drawingThread.displayX-(width/2);
		bottom=drawingThread.displayY-(height/2);	
	}

	@Override
	public void run() {
		flag=true;
		while (flag) {
			updateAllPositions();
			
			try {
				sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}
	
	private void updateAllPositions() {
		gravityX=GameActivity.getgX();
		gravityY=GameActivity.getgY();
		
		for (int i = 0; i < drawingThread.allRobots.size(); i++) {
			updateRobotsPosition(drawingThread.allRobots.get(i));
		}
		
		
	}

	private void updateRobotsPosition(Robot robot) {
		robot.centerX+=(robot.velocityX*timeConstant+0.5*gravityX*timeConstant*timeConstant);
		robot.velocityX+=gravityX*timeConstant;
		
		robot.centerY+=(robot.velocityY*timeConstant+0.5*gravityY*timeConstant*timeConstant);
		robot.velocityY+=gravityY*timeConstant;
		
		constrainPosition(robot);
		
	}

	private void constrainPosition(Robot robot) {
		//for x axis
		
		if (robot.centerX<left) {
			robot.centerX=left;
			robot.velocityX*=retardationRatio;
			
		}else if (robot.centerX>right) {
			robot.centerX=right;
			robot.velocityX*=retardationRatio;
			
		}
		
		//for y axis
		
		if (robot.centerY<top) {
			robot.centerY=top;
			robot.velocityY*=retardationRatio;
			
		}else if (robot.centerY>bottom) {
			robot.centerY=bottom;
			robot.velocityY*=retardationRatio;
			
		}
		
		
		
	}

	public void stopThread() {
		flag=false;
	}
	
}
