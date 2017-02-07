package challenges.binayaka.trees;

import challenges.binayaka.common.InstansceKeeper;
import processing.core.PApplet;

/**
 * This will generate the fractal plant
 * 
 * @author Binayaka
 *
 */
public class FractalPlant implements TreeGrammar {

	@Override
	public GrammerBuilder generateGrammar() {
		return GrammerBuilder.getBuilder("X").addRule("X", "F-[[X]+X]+F[+FX]-X").addRule("F", "FF")
				.withTurtleRule("F", TurtleRule.DrawForward()).withTurtleRule("-", TurtleRule.RotateLeftBySetAngle())
				.withTurtleRule("+", TurtleRule.RotateRightBySetAngle()).withTurtleRule("[", TurtleRule.PushState())
				.withTurtleRule("]", TurtleRule.RestoreState()).withTurtleInitRule(new TurtleRule() {

					@Override
					public void execute() {
						Turtle turtle = (Turtle) InstansceKeeper.getInstance(Turtle.class);
						if (turtle != null) {
							TurtleRule.setDECREMENT_RATIO(0.5f);
							TurtleRule.setAngle(PApplet.radians(25));
							turtle.getParent().clear();
							turtle.getParent().background(51);
							turtle.getParent().resetMatrix();
							float startY = turtle.getParent().height / 2 - (this.getGrammar().getGenerationCount());
							float startX = turtle.getParent().width / 2 - (this.getGrammar().getGenerationCount());
							turtle.getParent().translate(startX, startY);
							turtle.getParent().rotate(-TurtleRule.NINETY/3);
							turtle.getParent().stroke(255, 100);
						}
					}
				});
	}

}
