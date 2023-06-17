package ArkonoidGameZeynep;

import java.awt.Color;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class Paddle extends GameObjects 
{

	Paddle(int level) {
		super(level);
	}

	private int paddleX = 400; //starting position
	
	public void moveRight() {
		if(getX() >= 490) { //check if its outside the pannel
			setX(490);
		}
		else {
			paddleX += 20;
		}
	}
	
	public void moveLeft() {
		if(getX() <= 0) {
			setX(0);
		}
		else {
			paddleX -= 20;
		}
	}
	
	public int getX() {
		return paddleX;
	}
	
	public void setX(int x) {
		paddleX = x;
	}
	
	/*public void paint(){
	JLabel label= new JLabel();
	label.(Color.white);
	label.setOpaque(true);
	if(level == 1) {
		label.setBounds(paddleX, 600, 150, 8);
	}
	if(level == 2) {
		label.setBounds(paddleX, 600, 125, 8);
	}
	if(level == 3) {
		label.setBounds(paddleX, 600, 100, 8);
	}
	*/
	
	@Override
	public void paint(Graphics g) { 
		g.setColor(Color.white);
		if(level == 1) {
			g.fillRect(paddleX, 600, 150, 8);
		}
		if(level == 2) {
			g.fillRect(paddleX, 600, 125, 8);
		}
		if(level == 3) {
			g.fillRect(paddleX, 600, 100, 8);
		}
	}
	
	
	@Override
	public void paint(Graphics2D g) {}
	
}
	