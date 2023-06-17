package ArkonoidGameZeynep;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Ball extends GameObjects{
	
	Ball(int level) {
		super(level);
	}
	 //for ball's direction and position
	private int ballposX = 300;
	private int ballposY = 350;
	private int ballXdir = -1;
	private int ballYdir = -2;
	
	public void movement() { //for ball's movement
		ballposX += ballXdir; //detect if ball is touching left or right or not
		ballposY += ballYdir;
		
		if(ballposX < 0) { //llft border
			ballXdir = -ballXdir;
		}
		
		if(ballposX > 567) { //right border
			ballXdir = -ballXdir;
		}
		
		if(ballposY < 0) { //top border
			ballYdir = -ballYdir;
		}
	}
	/*public void paint(){
	JLabel label= new JLabel();
	label.(Color.yellow);
	label.setOpaque(true);
	label.drawOval(ballposX, ballposY, 20, 20); //I find out I have to use images for JLabel but I do not have time left
	}

	*/
	@Override
	public void paint(Graphics g) { //I tried it with JLabel but always something got wrong...
		g.setColor(Color.yellow);
		g.fillOval(ballposX, ballposY, 20, 20);
		
	}
	
	public void stopMovement() {
		ballXdir = 0;
		ballYdir = 0;
	}
	
	
	//getters and setters for all the variables 
	public int getPosX() {
		return ballposX;
	}
	
	public int getPosY() {
		return ballposY;
	}
	
	public int getPosXdir() {
		return ballXdir;
	}
	
	public int getPosYdir() {
		return ballYdir;
	}
	
	public void setPosY(int y) {
		ballposY = y;
	}
	
	public void changePosYdir() {
		ballYdir = -ballYdir;
	}
	
	public void changePosXdir() {
		ballXdir = -ballXdir;
	}

	@Override
	public void paint(Graphics2D g) {}
}