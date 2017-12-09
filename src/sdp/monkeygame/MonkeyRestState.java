package sdp.monkeygame;

import sdp.monkeygame.Constants;
import sdp.monkeygame.Monkey;
import sdp.monkeygame.MonkeyState;

/*Monkey in rest state
 * When the monkey is idle and the key is pressed, 
 * the new state of monkey is set to moving and the
 * MonkeyMoveState is returned
 */
public class MonkeyRestState extends MonkeyState {
	public MonkeyState keyUp(Monkey monkey) {
		monkey.setYCordinate(monkey.getYCordinate() - Constants.SQUARE_SIZE);
		monkey.setMonkeyState(new MonkeyMoveState());
		return new MonkeyMoveState();
	}
	public MonkeyState keyRight(Monkey monkey) {
		monkey.setXCordinate(monkey.getXCordinate() + Constants.SQUARE_SIZE);
		monkey.setMonkeyState(new MonkeyMoveState());
		return new MonkeyMoveState();
	}

	public MonkeyState keyDown(Monkey monkey) {
		monkey.setYCordinate(monkey.getYCordinate() + Constants.SQUARE_SIZE);
		monkey.setMonkeyState(new MonkeyMoveState());
		return new MonkeyMoveState();
	}

	public MonkeyState keyLeft(Monkey monkey) {
		monkey.setXCordinate(monkey.getXCordinate() - Constants.SQUARE_SIZE);
		monkey.setMonkeyState(new MonkeyMoveState());
		return new MonkeyMoveState();
	}

}
