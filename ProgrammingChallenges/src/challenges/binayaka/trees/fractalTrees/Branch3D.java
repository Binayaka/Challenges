package challenges.binayaka.trees.fractalTrees;

import processing.core.PVector;

/**
 * This will denote the branches of the trees
 * 
 * @author Binayaka
 *
 */
public class Branch3D {
	private Branch3D parent;
	private PVector pos;
	private PVector dir;
	int count = 0;
	private PVector saveDir;
	private float length = 5;

	/**
	 * Initialize the branch with a position, and direction
	 * 
	 * @param v
	 *            the position
	 * @param d
	 *            the direction
	 */
	public Branch3D(PVector v, PVector d) {
		parent = null;
		pos = v.copy();
		dir = d.copy();
		saveDir = dir.copy();
	}

	/**
	 * Generate a branch based on the given branch
	 * 
	 * @param p
	 *            the given branch
	 */
	public Branch3D(Branch3D p) {
		parent = p;
		pos = parent.next();
		dir = parent.dir.copy();
		saveDir = dir.copy();
	}

	public PVector getDir() {
		return dir;
	}

	public Branch3D getParent() {
		return parent;
	}

	public PVector getPos() {
		return pos;
	}

	public void reset() {
		count = 0;
		dir = saveDir.copy();
	}

	private PVector next() {
		PVector v = PVector.mult(dir, length);
		PVector next = PVector.add(pos, v);
		return next;
	}

}
