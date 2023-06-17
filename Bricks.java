package ArkonoidGameZeynep;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Bricks extends GameObjects{
	int brickCount = 24;
	int brickWidth, brickHeight;
	int level;
	int map[][];
	
	Bricks(int row, int col, int level){
		super(level);
		brickWidth = 490/col;
		brickHeight = 120/row;
		map = new int[row][col];
		for(int i = 0; i < map.length; i++) { //for rows
			if(level == 1) {
				for(int j = 0; j < map[0].length; j++) { //for columns
					map[i][j] = 1; //not intersected with a ball
				}
			}
			else if(level == 2) {
				for(int j = 0; j < map[0].length; j++) {
					map[i][j] = 2;
				}
			}
			else {
				for(int j = 0; j < map[0].length; j++) {
					map[i][j] = 3;
				}
			}
		}
	}
	
	public void paint(Graphics2D g) {
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[0].length; j++) {
				if(map[i][j] > 0) {
					if(level == 1) {
						g.setColor(Color.red);
						g.fillRect(j*brickWidth+48, i* brickHeight+40, brickWidth,brickHeight);
						g.setStroke(new BasicStroke(3)); //aralara boþluk için
						g.setColor(Color.magenta);
						g.drawRect(j*brickWidth+48, i* brickHeight+40, brickWidth,brickHeight);
					}
					else if (level == 2) {
						g.setColor(Color.orange);
						g.fillRect(j*brickWidth+48, i* brickHeight+40, brickWidth,brickHeight);
						g.setStroke(new BasicStroke(3));
						g.setColor(Color.blue);
						g.drawRect(j*brickWidth+48, i* brickHeight+40, brickWidth,brickHeight);
					}
					else {
						g.setColor(Color.black);
						g.fillRect(j*brickWidth+48, i* brickHeight+40, brickWidth,brickHeight);
						g.setStroke(new BasicStroke(3));
						g.setColor(Color.red);
						g.drawRect(j*brickWidth+48, i* brickHeight+40, brickWidth,brickHeight);
					}
				}
			}
		}
	}

	@Override
	public void paint(Graphics g) {}
}