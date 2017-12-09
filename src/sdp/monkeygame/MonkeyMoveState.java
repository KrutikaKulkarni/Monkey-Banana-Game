package sdp.monkeygame;

import sdp.monkeygame.Constants;

/* Monkey in movement
 * When the monkey is in movement and a key is pressed,
 * the state of the monkey continues 
 * to be moving and a MonkeyMoveState is returned
 * 
 * When the monkey is in movement and the key is released, 
 * the state of the monkey changes to rest and 
 * the MonkeyRestState is returned
 */
import sdp.monkeygame.Monkey;
import sdp.monkeygame.MonkeyState;

public class MonkeyMoveState extends MonkeyState {

	public MonkeyState keyUp(Monkey monkey) {
		monkey.setYCordinate(monkey.getYCordinate() - Constants.SQUARE_SIZE);
		monkey.setMonkeyState(this);
		return this;
	}
	public MonkeyState keyDown(Monkey monkey) {
		monkey.setYCordinate(monkey.getYCordinate() + Constants.SQUARE_SIZE);
		monkey.setMonkeyState(this);
		return this;
	}
	public MonkeyState keyLeft(Monkey monkey) {
		monkey.setXCordinate(monkey.getXCordinate() - Constants.SQUARE_SIZE);
		monkey.setMonkeyState(this);
		return this;
	}
	public MonkeyState keyRight(Monkey monkey) {
		monkey.setXCordinate(monkey.getXCordinate() + Constants.SQUARE_SIZE);
		monkey.setMonkeyState(this);
		return this;
	}
	public MonkeyState keyReleased(Monkey monkey) {
		monkey.setMonkeyState(new MonkeyRestState());
		return new MonkeyRestState();
	}
}
