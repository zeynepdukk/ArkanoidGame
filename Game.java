package ArkonoidGameZeynep;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel implements KeyListener, Runnable { //making it a panel, KeyListenr for arrow keys

	Paddle paddle;
	Ball ball;
	Bricks bricks;
	Thread animator;

	static int score;
	static int lives;

	boolean game;
	
	JFrame obj=new JFrame();

	Game(int level) {
		score = 0;
		lives = 3;
		game = true;

		paddle = new Paddle(level);
		ball = new Ball(level);
		bricks = new Bricks(3, 8, level);

		bricks.level = level;

		if (animator == null || !game) {
			animator = new Thread(this);
			animator.start();
		}
		
		obj.setSize(600,650);
		obj.setTitle("Arkanoid");		
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setLocationRelativeTo(null);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(this);
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}

	public void intersectsWithPaddle() {
		if (bricks.level == 1) {
			if (new Rectangle(ball.getPosX(), ball.getPosY(), 20, 20)
					.intersects(new Rectangle(paddle.getX(), 600, 150, 8))) {
				ball.changePosYdir();
			}
		} else if (bricks.level == 2) {
			if (new Rectangle(ball.getPosX(), ball.getPosY(), 20, 20)
					.intersects(new Rectangle(paddle.getX(), 600, 125, 8))) {
				ball.changePosYdir();
			}
		} else if (bricks.level == 3) {
			if (new Rectangle(ball.getPosX(), ball.getPosY(), 20, 20)
					.intersects(new Rectangle(paddle.getX(), 600, 100, 8))) {
				ball.changePosYdir();
			}
		}
	}

	public void intersectsWithBrick() {
		for (int i = 0; i < bricks.map.length; i++) {
			for (int j = 0; j < bricks.map[0].length; j++) {
				if (bricks.map[i][j] > 0) {
					int x = j * bricks.brickWidth + 48;
					int y = i * bricks.brickHeight + 40;
					int brickW = bricks.brickWidth;
					int brickH = bricks.brickHeight;

					Rectangle rect = new Rectangle(x, y, brickW, brickH);
					Rectangle ballRect = new Rectangle(ball.getPosX(), ball.getPosY(), 20, 20);

					if (ballRect.intersects(rect)) { //o positiondaki bricki kýrmak ve puan için
						bricks.map[i][j] = bricks.map[i][j] - 1;
						if (bricks.map[i][j] == 0) {
							bricks.brickCount--;
							if (bricks.level == 1) {
								score += 10;
							} else if (bricks.level == 2) {
								score += 50;
							} else {
								score += 250;
							}
						}
						ball.changePosYdir();
					}
				}
			}
		}
	}

	public void nextLevel(Graphics g) {
		if (bricks.brickCount == 0 && bricks.level == 1) {
			paddle = new Paddle(2);
			ball = new Ball(2);
			bricks = new Bricks(3, 8, 2);
			bricks.level = 2;
		} else if (bricks.brickCount == 0 && bricks.level == 2) {
			paddle = new Paddle(3);
			ball = new Ball(3);
			bricks = new Bricks(3, 8, 3);
			bricks.level = 3;
		} else if (bricks.brickCount == 0 && bricks.level == 2) {
			game = false;
			ball.stopMovement();
			g.setColor(Color.green);
			g.setFont(new Font("arial", Font.BOLD, 30));
			g.drawString("THE END, congrats!", 200, 300);

		}
	}

	public void decrementLife() {
		if (ball.getPosY() >= 610) {
			lives--;
			ball.setPosY(225);
		}
	}

	public void gameOver(Graphics g) {
		if (lives <= 0) {
			game = false;
			ball.stopMovement();
			g.setColor(Color.red);
			g.setFont(new Font("arial", Font.BOLD, 30));
			g.drawString("GAME OVER", 200, 300);
		}
	}

	public void gameFinished() {
		if(bricks.level == 3 && bricks.brickCount == 0) {
			game = false;
		}
	}

	public void saveToFile() {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
		Date dateObject = new Date();
		String username;
		username = JOptionPane.showInputDialog("Username: ", JOptionPane.OK_OPTION);
		FileWriter fw = null;

		try {
			fw = new FileWriter(new File("saveFile.txt"), true);
		} catch (IOException e_1) {
			System.out.println("exception!");
		}

		BufferedWriter br = new BufferedWriter(fw);
		PrintWriter pr = new PrintWriter(br);

		pr.println(username + " | Score: " + score + " | " + dateFormat.format(dateObject) + " | ");
		pr.close();

		try {
			br.close();
			fw.close();
		} catch (IOException e_2) {
			System.out.println("exception!");
		}
		obj.dispose();
		new MainMenu();
	}

	public void paint(Graphics g) {
		super.paint(g);

		// paints the background (the background changes if the level is changed)
		if (bricks.level == 1) {
			g.setColor(Color.magenta);
			g.fillRect(0, 0, 600, 650);
		}
		if (bricks.level == 2) {
			g.setColor(Color.blue);
			g.fillRect(0, 0, 600, 650);
		}
		if (bricks.level == 3) {
			g.setColor(Color.black);
			g.fillRect(0, 0, 600, 650);
		}

		// draws the score
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 20));
		g.drawString("Score: " + score, 10, 25);

		// draws the level status
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 20));
		g.drawString("Level: " + bricks.level, 500, 25);

		paddle.paint(g); // paints the paddle

		ball.paint(g); // paints the ball

		bricks.paint((Graphics2D) g); // paints the bricks

		// game methods
		if (game) {
			ball.movement();
			intersectsWithPaddle();
			intersectsWithBrick();
			nextLevel(g);
			decrementLife();
			gameOver(g);
			gameFinished();
		}
		
		// draws the life count
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 20));
		g.drawString("Lives: " + lives, 255, 25);
	
		g.dispose();
	}

	@SuppressWarnings("unused")
	@Override
	public void run() {
		long beforeTime, timeDiff, sleep;
		int animationDelay;
		
		beforeTime = System.currentTimeMillis();
		long time = System.currentTimeMillis();
		
		while (game) {
			if (bricks.level == 1) { //ball oynama hýzý için
				animationDelay = 7;
			} else if (bricks.level == 2) {
				animationDelay = 2;
			} else {
				animationDelay = -3;
			}
			repaint();
			try {
				time += animationDelay;
				Thread.sleep(Math.max(0, time - System.currentTimeMillis()));
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
		if (!game) {
			saveToFile();
		}
	}
//these methods are auto created by KeyListener
	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			paddle.moveRight();
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			paddle.moveLeft();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			paddle.moveRight();
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			paddle.moveLeft();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}