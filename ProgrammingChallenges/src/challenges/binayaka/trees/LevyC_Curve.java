package challenges.binayaka.trees;

import challenges.binayaka.common.InstansceKeeper;
import processing.core.PApplet;
import processing.core.PConstants;

/**
 * This will generate the Lévy C curve
 * 
 * @author Binayaka
 *
 */
public class LevyC_Curve implements TreeGrammar {

	@Override
	public GrammerBuilder generateGrammar() {
		return GrammerBuilder.getBuilder("F").addRule("F", "+F--F+").withTurtleRule("F", TurtleRule.DrawForward())
				.withTurtleRule("-", TurtleRule.RotateLeftBySetAngle())
				.withTurtleRule("+", TurtleRule.RotateRightBySetAngle()).withTurtleInitRule(new TurtleRule() {

					@Override
					public void execute() {
						Turtle turtle = (Turtle) InstansceKeeper.getInstance(Turtle.class);
						if (turtle != null) {
							TurtleRule.setDECREMENT_RATIO(0.8f);
							TurtleRule.setAngle(PApplet.radians(45));
							turtle.getParent().clear();
							turtle.getParent().background(51);
							turtle.getParent().resetMatrix();
							float startY = turtle.getParent().height / 2
									- (this.getGrammar().getGenerationCount() * 1.2f);
							float startX = turtle.getParent().width / 2
									- (this.getGrammar().getGenerationCount() * 1.2f);
							turtle.getParent().translate(startX, startY);
							turtle.getParent().rotate(TurtleRule.NINETY);
							turtle.getParent().stroke(255, 100);
						}
					}
				}).withTurtleEndRule(new TurtleRule() {

					@Override
					public void execute() {
						Turtle turtle = (Turtle) InstansceKeeper.getInstance(Turtle.class);
						if (turtle != null) {
							float zoom = 0, x = 0, y = 0;
							if (turtle.getParent().mouseButton == PConstants.LEFT) {
								x += (turtle.getParent().width / 2 - turtle.getParent().mouseX) / zoom;
								y += (turtle.getParent().height / 2 - turtle.getParent().mouseY) / zoom;
								zoom *= 1.5;
							} else {
								zoom = 1;
								x = 0;
								y = 0;
							}
							float zoomLevel = (float) (1.25 * zoom * this.getGrammar().getGenerationCount());
							turtle.getParent().pushMatrix();
							turtle.getParent().translate(turtle.getParent().width / 2 + x * zoomLevel,
									turtle.getParent().height / 2 + y * zoomLevel);
							turtle.getParent().scale(zoomLevel);
							turtle.getParent().popMatrix();
						}
					}
				});
	}

}
