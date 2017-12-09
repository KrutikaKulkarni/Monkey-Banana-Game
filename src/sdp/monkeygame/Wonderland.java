package sdp.monkeygame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Wonderland extends JPanel implements ActionListener, KeyListener{
	private static final long serialVersionUID = 1L;

	private Monkey monkey;
	private Banana banana;

	private static JFrame Gframe;
	private static JPanel controlPanel;
	private static JLabel timerLabel, bananaLabel, bananatimerLabel;
	Timer timer = new Timer(1000, this);

	private int GAME_TIMER = 50;

	private int BANANA_TIMER = 8;

	private int BANANAS_REMAINING = 8;

	private boolean isCollision;
	
	/* Initialize the game on starting the game */
	public static void main(String[] args) {
		initialize();
	}
	
	/*Create the Game UI and add all the elements*/
	private static void initialize() {

		Gframe = new JFrame("Monkey Banana game");
		bananaLabel = new JLabel("Number of bananas to be eaten = 8");
		timerLabel = new JLabel("Time remaining = 20");
		bananatimerLabel = new JLabel("Eat this banana within 6 s");
		createPanel();
		createGameFrame();
	}	private static void createPanel() {
		controlPanel = new JPanel();
		controlPanel.add(timerLabel);
		controlPanel.add(bananaLabel);
		controlPanel.add(bananatimerLabel);
		controlPanel.setBounds(0, 0, 500, 30);
		controlPanel.setBackground(Color.GREEN);
	}
	private static void createGameFrame() {
		Gframe.add(new Wonderland());
		Gframe.setBackground(Color.GREEN);
		Gframe.setResizable(false);
		Gframe.add(controlPanel, BorderLayout.NORTH);
		Gframe.pack();
		Gframe.setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
		Gframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Gframe.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent actionEvent) {
		if (BANANA_TIMER > 0) {
			BANANA_TIMER--;
			bananatimerLabel.setText("Eat this banana within " + BANANA_TIMER + "s");
		}
		if (BANANA_TIMER < 0) {
			updateBananaPosition();
		} else if (BANANA_TIMER == 0) {
			updateBananaPosition();
			BANANA_TIMER = 8;
			repaint();
		}
		if (GAME_TIMER > 0) {
			GAME_TIMER--;
			timerLabel.setText("  Time remaining = " + GAME_TIMER);
		} else {
			gameOver();
		}
	}
	/*Displaying texts for game over */
	public void gameOver() {
		timer.stop();
		timerLabel.setText("  Game over" + "Number of bananas eaten = " + (Constants.BANANAS - BANANAS_REMAINING));
		bananatimerLabel.setText("");
		if (BANANAS_REMAINING > 0) {
			bananaLabel.setText("You lose!!");
		} else {
			bananaLabel.setText("You win!!");
		}
		repaint();
	}
	/*Add monkey and banana in wonderland*/
	public Wonderland() {
		timer.start();
		monkey = new Monkey();
		banana = new Banana();
		updateBananaPosition();
		setBackground(Color.GREEN);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}
	/*Update the banana position*/
	public void updateBananaPosition() {
		banana.randomNumberGenerator();
	}
	
	/*paint monkey and banana on the frame*/ 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (GAME_TIMER > 0 && BANANAS_REMAINING > 0) {
			displayBanana(g);
			g.drawImage(monkey.getMonkeyImage(), monkey.getXCordinate(), monkey.getYCordinate(), Constants.IMAGE_SIZE, Constants.IMAGE_SIZE, this);
		}
	}
	
	public void displayBanana(Graphics g) {
		g.drawImage(banana.getBananaImage(), banana.getXCordinate(), banana.getYCordinate(), Constants.IMAGE_SIZE, Constants.IMAGE_SIZE, this);
	}
	
	/*Handle key board events and boundary conditions*/
	public void keyPressed(KeyEvent e) {

		int code = e.getKeyCode();

		/*keyboard up key is pressed*/
		if (code == KeyEvent.VK_UP) {
			if (monkey.getXCordinate() < 30) {
				monkey.setYCordinate(30);
			}
			monkey.keyUp(monkey);
		}
		
		/*keyboard down key is pressed*/
		if (code == KeyEvent.VK_DOWN) {
			if (monkey.getYCordinate() > 480) {
				monkey.setYCordinate(480);
			}
			monkey.keyDown(monkey);
		}
		
		/*keyboard left key is pressed*/
		if (code == KeyEvent.VK_LEFT) {
			if (monkey.getXCordinate() < 30) {
				monkey.setXCordinate(30);
			}
			monkey.keyLeft(monkey);
		}
		
		/*keyboard right key is pressed*/
		if (code == KeyEvent.VK_RIGHT) {
			if (monkey.getXCordinate() > 510) {
				monkey.setXCordinate(510);
			}
			monkey.keyRight(monkey);
		}

		isCollision = checkCollision();

		/*if banana and monkey are colliding update the banana position*/
		if (isCollision == true && BANANAS_REMAINING >= 0) {
			bananaLabel.setText("Number of bananas to be eaten = " + BANANAS_REMAINING);
			updateBananaPosition();
			BANANA_TIMER = 8;
		}
		
		/*if bananas to be eaten is 0, then game is over*/
		if (BANANAS_REMAINING == 0)
			gameOver();
		repaint();
	}
	
	public boolean checkCollision() {
		System.out.println("monkey n banana val " + monkey.getXCordinate() + " " + banana.getXCordinate() + " " + monkey.getYCordinate() + " "
				+ banana.getYCordinate());
		if (monkey.getXCordinate() == banana.getXCordinate() && monkey.getYCordinate() == banana.getYCordinate()) {
			System.out.println("inside coll");
			BANANAS_REMAINING--;
			return true;
		}
		return false;
	}
	
	
	public void keyTyped(KeyEvent e) {
	}

	
	public void keyReleased(KeyEvent arg0) {
		monkey.keyReleased(monkey);
	}

}
