package com.snake.components;

public class Score {

	private int score ;
	public Score() {
		score = 0 ;
	}
	
	public int getScore() {
		return score;
	}

	public String getAffiche() {
		return  "Your score is : " + score; 
	}
	
	
	public void setScore(int score) {
		this.score = score;
	}

}
