package sdp.monkeygame;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import sdp.monkeygame.MonkeyRestState;

public class Monkey {
	
	private int xCord;
	private int yCord;
	private BufferedImage monkeyImage;
	MonkeyState monkeyState;
	
	public int getXCordinate() {
		return xCord;
	}

	public int getYCordinate() {
		return yCord;
	}

	public void setXCordinate(int x) {
		this.xCord = x;
	}

	public void setYCordinate(int y) {
		this.yCord = y;
	}
	
	/* Initialize the monkey with coordinates (0,0)*/
	public Monkey() {
		monkeyState = new MonkeyRestState();
		xCord = 0;
		yCord = 0;
	}
	/* Pass the monkey state to set*/ 
	public void setMonkeyState(MonkeyState newMonkeyState) {
		monkeyState = newMonkeyState;
	}
	
	public BufferedImage getMonkeyImage() {
		URL monkeyResource = getClass().getResource("/monkey.png");
		try {
			monkeyImage = ImageIO.read(monkeyResource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return monkeyImage;
	}
	
	public MonkeyState keyUp(Monkey monkey) {
		return monkeyState.keyUp(this);
	}

	public MonkeyState keyDown(Monkey monkey) {
		return monkeyState.keyDown(this);
	}

	public MonkeyState keyLeft(Monkey monkey) {
		return monkeyState.keyLeft(this);
	}

	public MonkeyState keyRight(Monkey monkey) {
		return monkeyState.keyRight(this);
	}

	public MonkeyState keyReleased(Monkey monkey) {
		return monkeyState.keyReleased(this);
	}

	

}
