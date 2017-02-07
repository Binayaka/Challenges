package challenges.binayaka.trees;

import challenges.binayaka.common.InstansceKeeper;
import processing.core.PApplet;

/**
 * This is the l-System representation of the Sierpinski system.
 * 
 * @author Binayaka
 *
 */
public class SierpinskiTriangleOne implements TreeGrammar {

	@Override
	public GrammerBuilder generateGrammar() {
		return GrammerBuilder.getBuilder("F-G-G").addRule("G", "F-G+F+G-F").addRule("G", "GG")
				.withTurtleRule("F", TurtleRule.DrawForward()).withTurtleRule("G", TurtleRule.DrawForward())
				.withTurtleRule("+", TurtleRule.RotateLeftBySetAngle())
				.withTurtleRule("-", TurtleRule.RotateRightBySetAngle()).withTurtleInitRule(new TurtleRule() {

					@Override
					public void execute() {
						Turtle turtle = (Turtle) InstansceKeeper.getInstance(Turtle.class);
						if (turtle != null) {
							TurtleRule.setAngle(PApplet.radians(120));
							turtle.getParent().clear();
							turtle.getParent().background(51);
							turtle.getParent().resetMatrix();
							float startY = turtle.getParent().height / 2 - (this.getGrammar().getGenerationCount());
							float startX = turtle.getParent().width / 2;
							turtle.getParent().translate(startX, startY);
							turtle.getParent().rotate(TurtleRule.NINETY);
							turtle.getParent().stroke(255, 100);
						}
					}
				});
	}

}
