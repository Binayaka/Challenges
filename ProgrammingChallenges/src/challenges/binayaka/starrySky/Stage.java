package challenges.binayaka.starrySky;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Stage extends PApplet {

	int SPACE_PARTICLES = 500;
	List<Planet> planets = new ArrayList<>();
	List<Star> stars = new ArrayList<>();
	float TREE_ANGLE = PI / 5;
	int step = 0;
	int childCount = 13;
	Comet comet = null;

	List<TreeBranch> treeBranches = new ArrayList<>();
	List<TreeBranchTraversalCircle> traversals = new ArrayList<>();
	PImage image;

	@Override
	public void settings() {
		size(1280, 960);
	}

	@Override
	public void setup() {
		createSpaceObjects();
		createTheRootTreeBranch();
		image = loadImage("white_texture.png");
		image.resize(32, 32);
	}

	private void createTheRootTreeBranch() {
		PVector a = new PVector(width / 2, height - 50);
		PVector b = new PVector(width / 2, height - 200);
		TreeBranch br = new TreeBranch(this, a, b, null);
		treeBranches.add(br);
	}

	@Override
	public void draw() {
		background(0);
		showSpaceObjects();
		showTheDamnTree();
	}

	private void showTheDamnTree() {
		if (frameCount % 50 == 0) {
			// every 100 frames, increase the step
			step++;
			if (step < childCount) {
				for (int i = treeBranches.size() - 1; i >= 0; i--) {
					if (!treeBranches.get(i).finished) {
						treeBranches.add(treeBranches.get(i).branchA());
						treeBranches.add(treeBranches.get(i).branchB());
					}
					treeBranches.get(i).finished = true;
				}
			}
		}
		pushMatrix();
		translate(0, 0);
		for (int i = 0; i < treeBranches.size(); i++) {
			treeBranches.get(i).update();
			treeBranches.get(i).show();
		}
		for (int i = 0; i < traversals.size(); i++) {
			traversals.get(i).show();
			traversals.get(i).update();
		}
		popMatrix();
	}

	private void showSpaceObjects() {
		createGrass();
		showPlanets();
		twinkleStars();
		// showCometIfApplicable();
	}

	public void showCometIfApplicable() {
		PVector windAndGravity = new PVector(2f, 2f);
		if (comet == null) {
			// we have no visible comets
			float chance = random(0, 1);
			if (chance > 0.95) {
				// we add a 50-50 chance to spawn a comet
				PVector startingPos = new PVector(0, 0);
				comet = new Comet(this, 0, startingPos, image);
				comet.applyForce(windAndGravity);
				comet.show();
				for (int i = 0; i < 2; i++) {
					comet.addParticle();
				}
			}
		} else {
			// move the comet
			comet.applyForce(windAndGravity);
			comet.show();
			for (int i = 0; i < 2; i++) {
				comet.addParticle();
			}
			if (comet.isDead()) {
				comet = null;
			}
		}
	}

	private void showPlanets() {
		for (Planet pl : planets) {
			pl.show();
		}
	}

	/**
	 * This will create the ground grass
	 */
	private void createGrass() {
		pushMatrix();
		translate(0, 0);
		strokeWeight(1);
		stroke(255);
		int grassBaseHeight = height - 50;
		line(0, grassBaseHeight, width, grassBaseHeight);
		stroke(255, 90);
		float xStep = 10;
		float lastx = -999;
		float y = grassBaseHeight;
		int border = 5;
		for (int x = border; x <= width - border; x += xStep) {
			strokeWeight(1);
			y = grassBaseHeight - customRandom() * 10;
			float newx = x + customNoise() * xStep;
			if (lastx > -999) {
				line(newx, y, x, grassBaseHeight);
			}
			lastx = x;
		}
		popMatrix();
	}

	private float customRandom() {
		return (1 - pow(random(1), 5));
	}

	private float customNoise() {
		return (random(2) - 1);
	}

	/**
	 * This will create the space objects in the background
	 */
	private void createSpaceObjects() {
		// create more stars than planets?
		for (int i = 0; i < SPACE_PARTICLES; i++) {
			float val = random(0, 1);
			SpaceObject obj = null;
			if (val <= 0.6) {
				obj = calculateNewStar();
				stars.add((Star) obj);
			} else {
				obj = calculateNewPlanet();
				planets.add((Planet) obj);
			}
		}
	}

	private SpaceObject calculateNewPlanet() {
		return new Planet(this, randomSpaceObjectX(), randomSpaceObjectY());
	}

	private SpaceObject calculateNewStar() {
		return new Star(this, randomSpaceObjectX(), randomSpaceObjectY());
	}

	/**
	 * This will twinkle the stars
	 */
	private void twinkleStars() {
		for (Star obj : stars) {
			obj.update();
			obj.show();
		}
	}

	private float randomSpaceObjectX() {
		return random(0, width);
	}

	private float randomSpaceObjectY() {
		// we leave 100 pixels for the base
		return random(0, height - 100);
	}

	public void addNewTraversals(TreeBranchTraversalCircle e) {
		if (!traversals.contains(e)) {
			traversals.add(e);
		}
	}
}
