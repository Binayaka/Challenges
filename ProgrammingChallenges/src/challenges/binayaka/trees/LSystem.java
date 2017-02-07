package challenges.binayaka.trees;

import processing.core.PApplet;

/**
 * This will generate L-System trees
 * 
 * @author Binayaka
 *
 */
public class LSystem extends PApplet {
	GrammerBuilder grammar;

	@Override
	public void settings() {
		size(800, 600);
		Turtle.getTurtle(this);
	}

	@Override
	public void setup() {
		background(51);
		// grammar = new DefaultTree().generateGrammer();
		// grammar = new KochCurve().generateGrammar();
		// grammar = new CantorSet().generateGrammar();
		// grammar = new SierpinskiTriangleOne().generateGrammar();
		// grammar = new SierpinskiTriangleTwo().generateGrammar();
		// grammar = new DragonCurve().generateGrammar();
		// grammar = new FractalPlant().generateGrammar();
		grammar = new LevyC_Curve().generateGrammar();
	}

	@Override
	public void mousePressed() {
		super.mousePressed();
		grammar.generate();
		grammar.executeTurtleRules();
	}

	@Override
	public void draw() {

	}
}
