package challenges.binayaka.trees;

import challenges.binayaka.common.InstansceKeeper;

/**
 * This will draw the Dragon Curve
 * 
 * @author Binayaka
 *
 */
public class DragonCurve implements TreeGrammar {

	@Override
	public GrammerBuilder generateGrammar() {
		return GrammerBuilder.getBuilder("FX").addRule("X", "X+YF+").addRule("Y", "-FX-Y")
				.withTurtleRule("F", TurtleRule.DrawForward()).withTurtleRule("-", TurtleRule.RotateLeftNinety())
				.withTurtleRule("+", TurtleRule.RotateRightNinety()).withTurtleInitRule(new TurtleRule() {

					@Override
					public void execute() {
						Turtle turtle = (Turtle) InstansceKeeper.getInstance(Turtle.class);
						if (turtle != null) {
							TurtleRule.setDECREMENT_RATIO(0.9f);
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
