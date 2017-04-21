package challenges.binayaka.starrySky;

import challenges.binayaka.common.Drawable;

public class TreeBranchTraversalCircle implements Drawable {
	Stage main;
	TreeBranch root;
	TreeBranch parent;
	boolean parentTriggered = false;

	/*
	 * float biggerVal = 0; float smallerVal = 0; float startX, endX; float
	 * startY, endY; float slope, c;
	 */

	float t = 0;
	float x0, x1;
	float y0, y1;

	public TreeBranchTraversalCircle(Stage _main, TreeBranch _root, TreeBranch _parent) {
		main = _main;
		root = _root;
		parent = _parent;
		x1 = this.root.begin.x;
		x0 = this.root.end.x;
		y1 = this.root.begin.y;
		y0 = this.root.end.y;
	}

	@Override
	public void show() {
		if (t > 1) {
			if (!parentTriggered) {
				parentTriggered = true;
				if (parent != null && parent.parent != null) {
					main.addNewTraversals(new TreeBranchTraversalCircle(main, parent, parent.parent));
				}
			}
			return;
		}
		float newX = (1 - t) * x0 + t * x1;
		float newY = (1 - t) * y0 + t * y1;
		float dist = Stage.dist(x0, y0, x1, y1);
		float ellipseRad = Stage.map(dist, 0, 100, 1, 10);
		main.pushMatrix();
		main.noStroke();
		main.fill(255);
		main.ellipse(newX, newY, ellipseRad, ellipseRad);
		main.popMatrix();
	}

	@Override
	public void update() {
		t += 0.01f;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		result = prime * result + (parentTriggered ? 1231 : 1237);
		result = prime * result + ((root == null) ? 0 : root.hashCode());
		result = prime * result + Float.floatToIntBits(t);
		result = prime * result + Float.floatToIntBits(x0);
		result = prime * result + Float.floatToIntBits(x1);
		result = prime * result + Float.floatToIntBits(y0);
		result = prime * result + Float.floatToIntBits(y1);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TreeBranchTraversalCircle other = (TreeBranchTraversalCircle) obj;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		if (parentTriggered != other.parentTriggered)
			return false;
		if (root == null) {
			if (other.root != null)
				return false;
		} else if (!root.equals(other.root))
			return false;
		if (Float.floatToIntBits(t) != Float.floatToIntBits(other.t))
			return false;
		if (Float.floatToIntBits(x0) != Float.floatToIntBits(other.x0))
			return false;
		if (Float.floatToIntBits(x1) != Float.floatToIntBits(other.x1))
			return false;
		if (Float.floatToIntBits(y0) != Float.floatToIntBits(other.y0))
			return false;
		if (Float.floatToIntBits(y1) != Float.floatToIntBits(other.y1))
			return false;
		return true;
	}

}
