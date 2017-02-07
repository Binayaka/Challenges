package challenges.binayaka.trees;

import challenges.binayaka.common.InstansceKeeper;

public class CantorSet implements TreeGrammar {

	@Override
	public GrammerBuilder generateGrammar() {
		return GrammerBuilder.getBuilder("A").addRule("A", "ABA").addRule("B", "BBB")
				.withTurtleRule("A", TurtleRule.DrawForward()).withTurtleRule("B", TurtleRule.MoveForward())
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
