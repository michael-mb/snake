package com.snake.jeu;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.snake.audio.Audio;
import com.snake.components.Chrono;
import com.snake.components.Score;
import com.snake.components.Snake;


@SuppressWarnings("serial")
public class Scene extends JPanel{

	private final int HEIGHT = 500; 		// Stage height 
	private final int WIDTH = 600;			// Stage Width 
		
	private Chrono chrono;					// time 
	private Score score ;					// score
	private Font Scorefont ;				
	
	private Snake snakeHead ;				// snake head
	private Snake obstacle;					// food
	
	public Scene() {
		
		chrono = new Chrono(this);								// we launch the constant repaint of the scene
		score = new Score();								
		Scorefont = new Font("Arial", Font.BOLD, 15);		
		snakeHead = new Snake (WIDTH/2 , HEIGHT/2 , false);		// SnakeHead is created 
		snakeHead.getTails().add(snakeHead);					// Head is the first tail  
		this.setBackground(Color.black);
		createNewObstacle();									// first food 
		
		soundReplay();											//  Play Musique
	}
	
	
	public void paintComponent(Graphics g){
		 super.paintComponent(g);
		 Graphics2D g2 = (Graphics2D)g;
		 
		 //drawLine(g2);		// Draw grid System for visualisation 
		 drawScore(g2);
		 drawSnake(g2);
		 drawObstacle(g2);
		 if(chrono.isRun()) {
			 soundReplay();
		 }else{
			 drawEnd(g2);
		 }
	}
	
	/*****  It is unused : only for Grid visualisation *****/
	@SuppressWarnings("unused")
	private void drawLine(Graphics2D g2) {
		for(int i = 0 ; i< WIDTH ; i += 10) {
			g2.drawLine(i, 0, i, HEIGHT);	
		}
		for(int i = 0 ; i< HEIGHT+10 ; i += 10) {
			g2.drawLine(0, i, WIDTH, i);
		}
		
	}
	
	// Draw the score during the game
	private void drawScore(Graphics2D g2) {
		
		g2.setColor(Color.white);
		g2.setFont(Scorefont);
		g2.drawString(score.getAffiche(), 10  ,HEIGHT - 10); // on affiche le score 
	}
	
	// Draw the score at the end of the game
	private void drawEnd(Graphics2D g2) {
		g2.setColor(Color.white);
		g2.setFont(Scorefont);
		g2.drawString("GAME OVER ! "+ score.getAffiche(), WIDTH/2 - 150  ,HEIGHT /2); // on affiche le score 
	}
	
	// Draw the snake 
	private void drawSnake(Graphics2D g2) {
				
		g2.setColor(Color.white);
		
		
		// we observe if there is contact with the obstacle ( food )
		if(snakeHead.contact(obstacle) == true) {
			obstacle.setInLife(false);
			createNewObstacle();
			snakeHead.eat();
			score.setScore(score.getScore() + 1);
		}
		
		
		// Reset positions when exceeding limits
		if(snakeHead.getxPos() > WIDTH) {
			snakeHead.setxPos(0);
		}
		if(snakeHead.getyPos() > HEIGHT) {
			snakeHead.setyPos(0);
		}
		if(snakeHead.getxPos() < 0) {
			snakeHead.setxPos(WIDTH);
		}
		if(snakeHead.getyPos() < 0) {
			snakeHead.setyPos(HEIGHT);
		}
		
		// update snake position
		snakeHead.update();
		
		// we observe if there is contact between the snake and himself - (if he is dead)
		for(Snake s : snakeHead.getTails()) {
			if(s.contact(snakeHead)) {
				if(!s.equals(snakeHead))
				{
					chrono.setRun(false);
				}
			}			
		}
		
		for(Snake s : snakeHead.getTails()) {
			g2.setColor(Color.white);
			g2.fillRect(s.getxPos(), s.getyPos(), 10, 10);
			g2.setColor(Color.black);
			g2.drawRect(s.getxPos(), s.getyPos(), 10, 10);
		}	
	}
	
	private void drawObstacle(Graphics2D g2) {
		g2.setColor(Color.yellow);
		if(obstacle.isInLife()) {
			g2.fillRect(obstacle.getxPos(), obstacle.getyPos(), 10, 10);
		}

	}
	
	// Create a new obstacle ( food ) 
	private void createNewObstacle() {
		
		int positionAleatoireX = conversionCoord(10 + (int)(Math.random() * ((WIDTH - 20) + 10)));
		int positionAleatoireY = conversionCoord(10 + (int)(Math.random() * ((HEIGHT - 20) + 10)));
		
		obstacle = new Snake(positionAleatoireX , positionAleatoireY , true);
	}
	
	// convert coordinates so that they pass through the grid system
	private int conversionCoord(int toParse) {
		int number = toParse - (toParse)%10 ;
		
		return number ;
	}
	
	public void setSnakeDirection(String direction) {
		snakeHead.setDirection(direction);	
	}
	
	public String getSnakeDirection() {
		return snakeHead.getDirection();
	}
	
	// Replay Music 
	public void soundReplay() {
		if(chrono.getTime()>= 1200) {
			Chrono.resetTime();
			Audio.playSound("/audio/snake.wav");
		}
	}
	
	
}
