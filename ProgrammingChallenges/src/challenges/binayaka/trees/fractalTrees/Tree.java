package challenges.binayaka.trees.fractalTrees;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * This will denote out fractal tree
 * 
 * @author Binayaka
 *
 */
public class Tree {
	private static final int maxLeaves = 2000;
	private PApplet core;
	float min_dist = 5;
	float max_dist = 200;

	ArrayList<Branch3D> branches = new ArrayList<Branch3D>();
	ArrayList<Leaf> leaves = new ArrayList<Leaf>();

	public Tree(PApplet main) {
		core = main;
		for (int i = 0; i < maxLeaves; i++) {
			leaves.add(new Leaf(core));
		}
		Branch3D root = new Branch3D(new PVector(0/* core.width / 2 */, core.height / 2), new PVector(0, -1));
		branches.add(root);
		Branch3D current = new Branch3D(root);
		while (!closeEnough(current)) {
			Branch3D trunk = new Branch3D(current);
			branches.add(trunk);
			current = trunk;
		}
	}

	private boolean closeEnough(Branch3D b) {
		for (Leaf l : leaves) {
			float d = PVector.dist(b.getPos(), l.getPos());
			if (d < max_dist) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This will show the tree
	 */
	public void show() {
		for (Leaf l : leaves) {
			l.show();
		}
		for (int i = 0; i < branches.size(); i++) {
			Branch3D b = branches.get(i);
			if (b.getParent() != null) {
				/*
				 * core.stroke(255); core.line(b.getPos().x, b.getPos().y,
				 * b.getParent().getPos().x, b.getParent().getPos().y);
				 */
				float sw = PApplet.map(i, 0, branches.size(), 6, 0);
				core.strokeWeight(sw);
				core.stroke(255);

				core.line(b.getPos().x, b.getPos().y, b.getPos().z, b.getParent().getPos().x, b.getParent().getPos().y,
						b.getParent().getPos().z);

				/*
				 * core.pushMatrix(); core.translate(b.getPos().x, b.getPos().y,
				 * b.getPos().z); float height = b.getPos().z -
				 * b.getParent().getPos().z;
				 * DrawUtils.getInstance().drawCylinder(core, 1, 1, height, 3);
				 * core.popMatrix();
				 */
			}
		}
	}

	public void grow() {
		for (Leaf l : leaves) {
			Branch3D closest = null;
			PVector closestDir = null;
			float record = -1;

			for (Branch3D b : branches) {
				PVector dir = PVector.sub(l.getPos(), b.getPos());
				float d = dir.mag();
				if (d < min_dist) {
					l.reached();
					closest = null;
					break;
				} else if (d > max_dist) {

				} else if (closest == null || d < record) {
					closest = b;
					closestDir = dir;
					record = d;
				}
			}
			if (closest != null) {
				closestDir.normalize();
				closest.getDir().add(closestDir);
				closest.count++;
			}
		}

		for (int i = leaves.size() - 1; i >= 0; i--) {
			if (leaves.get(i).isReached()) {
				leaves.remove(i);
			}
		}

		for (int i = branches.size() - 1; i >= 0; i--) {
			Branch3D b = branches.get(i);
			if (b.count > 0) {
				b.getDir().div(b.count);
				PVector rand = PVector.random2D();
				rand.setMag((float) 0.3);
				b.getDir().add(rand);
				b.getDir().normalize();
				Branch3D newB = new Branch3D(b);
				branches.add(newB);
				b.reset();
			}
		}
	}

}
