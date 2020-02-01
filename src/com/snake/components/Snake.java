package com.snake.components;

import java.util.ArrayList;

public class Snake {
	
	private int yPos ;
	private int xPos ;
	private String direction ;
	private boolean inLife ;
	private ArrayList<Snake> tail;

	
	public Snake (int xPos , int yPos , boolean inLife) {
		this.xPos = xPos ;
		this.yPos = yPos ;
		this.inLife = inLife ;
		this.direction = "E" ; 
		this.tail = new ArrayList<>();
	}

	
	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	
	public boolean isInLife() {
		return this.inLife ;
	}
	public void setInLife(boolean inLife) {
		this.inLife = inLife ;
	}
	
	public void setDirection(String direction) {
		this.direction = direction ;
	}
	public String getDirection() {
		return direction ;
	}
	
	public ArrayList<Snake> getTails(){
		return this.tail;
	}
	
	// Return true if there is a contact with Tail
	public boolean contact(Snake snake) {
		if(this.getxPos() == snake.xPos && this.yPos == snake.yPos)
			return true ;
		return false ;
	}
	
	 
	public void eat() {
		getTails().add(new Snake(this.getxPos(),this.getyPos(), false ));
	}
	
	
	// Calculates the new positions of the snake and its tail
	public void update () {
				
		for(int i = tail.size() -1 ; i>0 ; i--) {
			Snake snake;
			snake = tail.get(i-1);
			int px = snake.xPos;
			int py = snake.yPos;
			
			snake = tail.get(i);
			snake.setxPos(px);
			snake.setyPos(py);
		}
		
		if(getDirection().equals("E"))
			setxPos(getxPos()+10);
		
		else if(getDirection().equals("O"))
			setxPos(getxPos()-10);
		
		else if(getDirection().equals("N"))
			setyPos(getyPos()-10);
		
		else
			setyPos(getyPos()+10);
	}
}
