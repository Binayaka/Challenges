package challenges.binayaka.trees.fractalTrees;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PVector;

public class Tree2D extends PApplet {

	List<Branch> tree = new ArrayList<>();
	List<PVector> leaves = new ArrayList<>();
	int count = 0;

	@Override
	public void settings() {
		size(800, 600);
	}

	@Override
	public void setup() {
		PVector a = new PVector(width / 2, height);
		PVector b = new PVector(width / 2, height - 100);
		Branch br = new Branch(this, a, b);
		tree.add(br);
	}

	@Override
	public void draw() {
		background(51);
		for (int i = 0; i < tree.size(); i++) {
			tree.get(i).update();
			tree.get(i).show();
		}

		/*
		 * for (int i = 0; i < leaves.size(); i++) { fill(255, 0, 100, 100);
		 * noStroke(); ellipse(leaves.get(i).x, leaves.get(i).y, 8, 8);
		 * leaves.get(i).y += random(0, 2); }
		 */
	}

	@Override
	public void mousePressed() {
		super.mousePressed();
		for (int i = tree.size() - 1; i >= 0; i--) {
			if (!tree.get(i).finished) {
				tree.add(tree.get(i).branchA());
				tree.add(tree.get(i).branchB());
			}
			tree.get(i).finished = true;
		}
		count++;
		/*
		 * // if (count == 6) { if (count % 6 == 0) { for (int i = 0; i <
		 * tree.size(); i++) { if (!tree.get(i).finished) { PVector leaf =
		 * tree.get(i).end.copy(); leaves.add(leaf); } } }
		 */

	}
}
