package challenges.binayaka.trees;

import challenges.binayaka.common.InstansceKeeper;
import processing.core.PApplet;

/**
 * This will define the rules for the turtle
 * 
 * @author Binayaka
 *
 */
public abstract class TurtleRule {

	public GrammerBuilder getGrammar() {
		return grammar;
	}

	public void setGrammar(GrammerBuilder grammar) {
		this.grammar = grammar;
	}

	private static int lineLength = 200;
	private static float angle = PApplet.radians(25);
	public static float NINETY = PApplet.radians(90);
	public static float DECREMENT_RATIO = 0.5f;
	private GrammerBuilder grammar;

	public static int getLineLength() {
		return lineLength;
	}

	public static void setLineLength(int lineLength) {
		TurtleRule.lineLength = lineLength;
	}

	public static float getAngle() {
		return angle;
	}

	public static void setAngle(float angle) {
		TurtleRule.angle = angle;
	}

	public static float getDECREMENT_RATIO() {
		return DECREMENT_RATIO;
	}

	public static void setDECREMENT_RATIO(float _DECREMENT_RATIO) {
		DECREMENT_RATIO = _DECREMENT_RATIO;
	}

	/**
	 * This will be the rule that is called
	 */
	public abstract void execute();

	/**
	 * This will move the registered turtle forward
	 */
	public static TurtleRule DrawForward() {
		TurtleRule rule = new TurtleRule() {

			@Override
			public void execute() {
				Turtle turtle = (Turtle) InstansceKeeper.getInstance(Turtle.class);
				if (turtle != null) {
					turtle.getParent().line(0, 0, 0, -lineLength);
					turtle.getParent().translate(0, -lineLength);
				}
			}
		};
		return rule;
	}

	/**
	 * This will move the turtle forward
	 * 
	 * @return
	 */
	public static TurtleRule MoveForward() {
		TurtleRule rule = new TurtleRule() {

			@Override
			public void execute() {
				Turtle turtle = (Turtle) InstansceKeeper.getInstance(Turtle.class);
				if (turtle != null) {
					turtle.getParent().translate(0, -lineLength);
				}
			}
		};
		return rule;
	}

	/**
	 * This will rotate the registered turtle to the right
	 */
	public static TurtleRule RotateRightBySetAngle() {
		TurtleRule rule = new TurtleRule() {

			@Override
			public void execute() {
				Turtle turtle = (Turtle) InstansceKeeper.getInstance(Turtle.class);
				if (turtle != null) {
					turtle.getParent().rotate(angle);
				}
			}
		};

		return rule;
	}

	/**
	 * This will move the registered turtle to the left
	 */
	public static TurtleRule RotateLeftBySetAngle() {
		TurtleRule rule = new TurtleRule() {

			@Override
			public void execute() {
				Turtle turtle = (Turtle) InstansceKeeper.getInstance(Turtle.class);
				if (turtle != null) {
					turtle.getParent().rotate(-angle);
				}
			}
		};
		return rule;
	}

	/**
	 * This will rotate by ninety degrees to the left
	 * 
	 * @return
	 */
	public static TurtleRule RotateLeftNinety() {
		TurtleRule rule = new TurtleRule() {

			@Override
			public void execute() {
				Turtle turtle = (Turtle) InstansceKeeper.getInstance(Turtle.class);
				if (turtle != null) {
					turtle.getParent().rotate(-NINETY);
				}
			}
		};
		return rule;
	}

	/**
	 * This will rotate by ninety degrees to the right
	 * 
	 * @return
	 */
	public static TurtleRule RotateRightNinety() {
		TurtleRule rule = new TurtleRule() {

			@Override
			public void execute() {
				Turtle turtle = (Turtle) InstansceKeeper.getInstance(Turtle.class);
				if (turtle != null) {
					turtle.getParent().rotate(NINETY);
				}
			}
		};
		return rule;
	}

	/**
	 * This will save the state of the turtle
	 */
	public static TurtleRule PushState() {
		TurtleRule rule = new TurtleRule() {

			@Override
			public void execute() {
				Turtle turtle = (Turtle) InstansceKeeper.getInstance(Turtle.class);
				if (turtle != null) {
					turtle.getParent().pushMatrix();
				}
			}
		};
		return rule;
	}

	/**
	 * This will restore the state of the turtle
	 */
	public static TurtleRule RestoreState() {
		TurtleRule rule = new TurtleRule() {

			@Override
			public void execute() {
				Turtle turtle = (Turtle) InstansceKeeper.getInstance(Turtle.class);
				if (turtle != null) {
					turtle.getParent().popMatrix();
				}
			}
		};
		return rule;
	}

}
