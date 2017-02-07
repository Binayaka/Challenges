package challenges.binayaka.trees.fractalTrees;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * This will denote the leaves of the fractal tree
 * 
 * @author Binayaka
 *
 */
public class Leaf {
	private PApplet parent;
	private PVector pos;
	private boolean reached = false;
	private final float leafWidth = 4;
	private final float leafHeight = 4;

	public Leaf(PApplet main) {
		parent = main;
		pos = PVector.random3D();
		pos.mult(parent.random(parent.width / 2));		
		pos.y -= parent.height / 4;
	}

	public PVector getPos() {
		return pos;
	}

	public void reached() {
		reached = true;
	}

	public boolean isReached() {
		return reached;
	}

	public void show() {
		parent.fill(255);
		parent.noStroke();
		parent.pushMatrix();
		parent.translate(pos.x, pos.y, pos.z);
		//parent.sphere(leafHeight);
		parent.ellipse(0, 0, leafWidth, leafHeight);
		parent.popMatrix();
	}

}
