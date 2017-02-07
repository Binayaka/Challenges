package challenges.binayaka.trees;

import challenges.binayaka.common.InstansceKeeper;
import processing.core.PApplet;

/**
 * This will generate the Sierpinski arrowhead curve
 * 
 * @author Binayaka
 *
 */
public class SierpinskiTriangleTwo implements TreeGrammar {

	@Override
	public GrammerBuilder generateGrammar() {
		return GrammerBuilder.getBuilder("A").addRule("A", "+B-A-B+").addRule("B", "-A+B+A-")
				.withTurtleRule("A", TurtleRule.DrawForward()).withTurtleRule("B", TurtleRule.DrawForward())
				.withTurtleRule("+", TurtleRule.RotateLeftBySetAngle())
				.withTurtleRule("-", TurtleRule.RotateRightBySetAngle()).withTurtleInitRule(new TurtleRule() {

					@Override
					public void execute() {
						Turtle turtle = (Turtle) InstansceKeeper.getInstance(Turtle.class);
						if (turtle != null) {
							TurtleRule.setAngle(PApplet.radians(60));
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
