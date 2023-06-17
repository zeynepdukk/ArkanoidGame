package ArkonoidGameZeynep;

import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class GameObjects {
	
	int level; 
	
	GameObjects(int level){
		this.level = level;
	}
	
	public abstract void paint(Graphics2D g); //for bricks
	public abstract void paint(Graphics g); //for the paddle and the ball
}
