package challenges.binayaka.trees;

/**
 * This is our implementation of the default tree
 * 
 * @author Binayaka
 *
 */
public class DefaultTree implements TreeGrammar {

	@Override
	public GrammerBuilder generateGrammar() {
		return GrammerBuilder.getBuilder("F").addRule("F", "FF+[+F-F-F]-[-F+F+F]")
				.withTurtleRule("F", TurtleRule.DrawForward()).withTurtleRule("+", TurtleRule.RotateRightBySetAngle())
				.withTurtleRule("-", TurtleRule.RotateLeftBySetAngle()).withTurtleRule("[", TurtleRule.PushState())
				.withTurtleRule("]", TurtleRule.RestoreState());
	}

}
