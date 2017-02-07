package challenges.binayaka.trees;

import challenges.binayaka.common.InstansceKeeper;

public class KochCurve implements TreeGrammar {

	@Override
	public GrammerBuilder generateGrammar() {
		return GrammerBuilder.getBuilder("F").addRule("F", "F+F-F-F+F").withTurtleRule("F", TurtleRule.DrawForward())
				.withTurtleRule("+", TurtleRule.RotateLeftNinety()).withTurtleRule("-", TurtleRule.RotateRightNinety())
				.withTurtleInitRule(new TurtleRule() {

					@Override
					public void execute() {
						Turtle turtle = (Turtle) InstansceKeeper.getInstance(Turtle.class);
						if (turtle != null) {
							turtle.getParent().clear();
							turtle.getParent().background(51);
							turtle.getParent().resetMatrix();
							float startY = turtle.getParent().height * 3 / 4;
							float shiftDistance = this.getGrammar().getGenerationCount() * 100.0f;
							float startX = turtle.getParent().width / 2 - shiftDistance;
							turtle.getParent().translate(startX, startY);
							turtle.getParent().rotate(TurtleRule.NINETY);
							turtle.getParent().stroke(255, 100);
						}
					}
				});
	}

}
