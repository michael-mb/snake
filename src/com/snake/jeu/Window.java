package com.snake.jeu;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Window extends JFrame{

	private Scene myScene ; 
	
	public Window() {

		initParts();
		initWindow();
		initListener();
		
		this.setVisible(true);
	}
	
	
	private void initParts() {
		myScene = new Scene();
	}
	private void initWindow() {
	
		this.setTitle("SNAKE ... by Micky");
		this.setSize(600,522);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setAlwaysOnTop(false);
		this.setLocationRelativeTo(null);
		this.add(myScene , BorderLayout.CENTER);
		
	}
	
	private void initListener() {
		keyboardListener clavierListener = new keyboardListener();
		this.addKeyListener(clavierListener);
	}
		
	class keyboardListener implements KeyListener{

		    public void keyPressed(KeyEvent event) {
		    	//System.out.println("Code touche relâchée : " + event.getKeyCode() + " - caractère touche relâchée : " + event.getKeyChar());           
		    }

		    public void keyReleased(KeyEvent event) {
		    	if(event.getKeyCode() == 37) {
		    		if(!(myScene.getSnakeDirection() == "E")) {
			    		myScene.setSnakeDirection("O");
		    		}
		    	}
		    	else if (event.getKeyCode() == 38) {
		    		if(!(myScene.getSnakeDirection() == "S")) {
		    			myScene.setSnakeDirection("N");
		    		}
		    		
		    	}
		    	else if (event.getKeyCode() == 39) {
		    		if(!(myScene.getSnakeDirection() == "O")) {
			    		myScene.setSnakeDirection("E");
		    		}
		    	}
		    	else {
		    		if(!(myScene.getSnakeDirection() == "N")) {
			    		myScene.setSnakeDirection("S");
		    		}
		    	}	              
		    }
		    public void keyTyped(KeyEvent event) {}   	
	}
	  
}
