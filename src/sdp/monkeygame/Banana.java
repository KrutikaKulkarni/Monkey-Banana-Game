package sdp.monkeygame;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;


public class Banana {
	
	private int xCord;
	private int yCord;
	private BufferedImage bananaImage;
	private Random random;
	
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
	/*Set the banana image stored in resource folder*/
	public BufferedImage getBananaImage() {
		URL bananaResource = getClass().getResource("/banana.png");
		try {
			bananaImage = ImageIO.read(bananaResource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bananaImage;
	}
	/*Calculate banana co-ordinates using Java.util.Random function */ 
	public Banana randomNumberGenerator() {
		random = new Random();
		xCord = random.nextInt(19) * Constants.SQUARE_SIZE;
		yCord = random.nextInt(19) * Constants.SQUARE_SIZE;
		if (xCord >= Constants.FRAME_WIDTH || yCord >= Constants.FRAME_HEIGHT)
			randomNumberGenerator();
		return this;
	}
	
	
	


}
