package ArkonoidGameZeynep;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;


@SuppressWarnings("serial")
public class MainMenu extends JFrame implements ActionListener, KeyListener {

	JLayeredPane layeredPane, lp; //main menu ve level penceresi icin ayrý
	Image img;
	int width = 600;
	int height = 650;

	MainMenu() {
		buildMenu();

		setTitle("MENU");
		setSize(width, height);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void buttonBuilder(JButton button) {
		button.setBackground(Color.red);
		button.setForeground(Color.yellow);

		button.setFocusPainted(false);
		button.setFont(new Font("arial", Font.BOLD, 20));
	}

	public void buildMenu() {

		getContentPane().removeAll();

		try {
			// I made my own background in AdobeIllustrator
			img = ImageIO.read(new File("C:\\Users\\zeyne\\ImageArkanoid\\ArkanoidBackground.jpg")).getScaledInstance(width, height,
					Image.SCALE_DEFAULT);
		} catch (IOException e1) {
			System.out.println("exception!");
		} 

		layeredPane = new JLayeredPane() {
			public void paintComponent(Graphics g) {
				g.drawImage(img, 0, 0, null);
			}
		};

		final JButton backToMenu = new JButton("Back");
		buttonBuilder(backToMenu);

		JButton newGame = new JButton("New Game");
		buttonBuilder(newGame);
		newGame.setBounds(200, 80, 200, 50);
		layeredPane.add(newGame);

		final JButton lvlButton1 = new JButton("Level 1");
		buttonBuilder(lvlButton1);
		lvlButton1.setBounds(200, 50, 200, 50);

		final JButton lvlButton2 = new JButton("Level 2");
		buttonBuilder(lvlButton2);
		lvlButton2.setBounds(200, 120, 200, 50);

		final JButton lvlButton3 = new JButton("Level 3");
		buttonBuilder(lvlButton3);
		lvlButton3.setBounds(200, 190, 200, 50);

		JButton options = new JButton("Options");
		buttonBuilder(options);
		options.setBounds(200, 160, 200, 50);
		layeredPane.add(options);

		JButton scores = new JButton("Scores");
		buttonBuilder(scores);
		scores.setBounds(200, 240, 200, 50);
		layeredPane.add(scores);

		JButton help = new JButton("Help");
		buttonBuilder(help);
		help.setBounds(200, 320, 200, 50);
		layeredPane.add(help);

		JButton about = new JButton("About");
		buttonBuilder(about);
		about.setBounds(200, 400, 200, 50);
		layeredPane.add(about);

		JButton exit = new JButton("Exit");
		buttonBuilder(exit);
		exit.setBounds(200, 480, 200, 50);
		layeredPane.add(exit);

		add(layeredPane);

		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();

				try {
					img = ImageIO.read(new File("C:\\Users\\zeyne\\ImageArkanoid\\arcade.jpg")).getScaledInstance(width,
							height, Image.SCALE_DEFAULT);
				} catch (IOException e1) {
					System.out.println("exception!");
				}

				lp = new JLayeredPane() {
					public void paintComponent(Graphics g) {
						g.drawImage(img, 0, 0, null);
					}
				};
				
				backToMenu.setBounds(200, 530, 200, 50);
				lp.add(backToMenu);

				lp.add(lvlButton1);
				lp.add(lvlButton2);
				lp.add(lvlButton3);

				add(lp);
				setVisible(true);
			}
		});

		backToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buildMenu();
				setVisible(true);
			}
		});

		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"Name: Zeynep Dükkancýoðlu\n" + "Student ID: 20200702076\n"
								+ "E-Mail: zeynep.dukkancioglu@std.yeditepe.edu.tr\n",
						"Developers info", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Use the arrow keys to control the paddle.\n", "Help Section",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		options.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "To choose a game level, just press the 'New Game' button.\n"
						+ "All the other options are already listed in the Main Menu.\n"
						, "Option Panel",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		scores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				
				try {
					img = ImageIO.read(new File("gameImages/background.jpg")).getScaledInstance(width,
							height, Image.SCALE_DEFAULT);
				} catch (IOException e1) {
					System.out.println("exception!");
				}
				
				lp = new JLayeredPane() {
					public void paintComponent(Graphics g) {
						g.drawImage(img, 0, 0, null);
					}
				};
				File users = new File("saveFile.txt");
				BufferedReader br = null;
				try {
					br = new BufferedReader(new FileReader(users));
				} 
				catch (FileNotFoundException e1) {
					System.out.println("exception!");
				} 
				String string; 
				int index=0,previndex=0;
				int x = 10;
				int y = 10;
				try {
					while ((string = br.readLine()) != null) {
						index = string.indexOf('|');
						String name = string.substring(0, index);
						previndex = index;
						index = string.indexOf('|',index+1);
						String score = string.substring(previndex+1,index);
						previndex = index;
						index = string.indexOf('|',index+1);
						String date = string.substring(previndex+1,index);
						JLabel label = new JLabel("Name: "+ name +""+score+"Date:"+ date) ;
						label.setForeground(Color.white);
						label.setFont(new Font("arial", Font.BOLD,20));
						label.setBounds(x, y, 500, 100);
						y+=50;
						lp.add(label);
					}
				} catch (IOException e1) {
					
					System.out.println("exception!");
				}
			
				backToMenu.setBounds(200, 530, 200, 50);
				lp.add(backToMenu);
				
				
				add(lp);
				setVisible(true);
			}
		});
		
		lvlButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Game(1);
			}
		});
		
		lvlButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Game(2);
			}
		});
		
		lvlButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Game(3);
			}
		});

		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void actionPerformed(ActionEvent e) {}

	public static void main(String[] args) {
		new MainMenu();
	}
}