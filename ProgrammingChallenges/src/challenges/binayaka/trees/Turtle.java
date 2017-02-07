package challenges.binayaka.trees;

import challenges.binayaka.common.InstansceKeeper;
import processing.core.PApplet;

/**
 * This will draw our generated L-System string
 * 
 * @author Binayaka
 *
 */
public class Turtle {

	private final PApplet parent;

	private Turtle(PApplet main) {
		parent = main;
		InstansceKeeper.registerInstance(getClass(), this);
	}

	public PApplet getParent() {
		return parent;
	}

	public static Turtle getTurtle(PApplet main) {
		return new Turtle(main);
	}

	/**
	 * This will draw on the screen based on the string
	 */
	public void init() {
		parent.clear();
		parent.background(51);
		parent.resetMatrix();
		parent.translate(parent.width / 2, parent.height);
		parent.stroke(255, 100);
	}

	/**
	 * This will execute the grammar, for the number of generations, and draw
	 * the result
	 * 
	 * @param grammar
	 * @param generations
	 */
	public void executeGrammer(GrammerBuilder grammar, int generations) {
		if (grammar != null) {
			for (int i = 0; i < generations; i++) {
				grammar.generate();
			}
			grammar.executeTurtleRules();
		}
	}

}
