package challenges.binayaka.trees.fractalTrees;

import peasy.PeasyCam;
import processing.core.PApplet;

public class SpaceColonization extends PApplet {
	private Tree tree;
	PeasyCam cam;

	@Override
	public void settings() {
		size(600, 900, P3D);
	}

	@Override
	public void setup() {
		tree = new Tree(this);
		cam = new PeasyCam(this, 500);
	}

	@Override
	public void draw() {
		background(51);
		tree.show();
		tree.grow();
	}
}
