package com.snake.components;

import com.snake.jeu.Scene;

public class Chrono implements Runnable {

	private Scene scene ;
	private boolean run = true  ;
	private final int PAUSE = 100 ;
	private static int time = 1201 ;
	
	public Chrono(Scene scene) {
		this.scene = scene ;
		
		Thread thread = new Thread(this);
		thread.start();
	}
	@Override
	public void run() {
		
		while(run) {
			scene.repaint();
			
			try {
				Thread.sleep(PAUSE);
				time ++ ;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void resetTime() {
		 time = 0 ;
	}
	
	public int getTime() {
		return time ;
	}
	public boolean isRun() {
		return run ;
	}
	public void setRun(boolean run) {
		this.run = run ;
	}

}
