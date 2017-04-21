package challenges.binayaka.snowField;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Horizon extends PApplet {
	int initialCount = 300;
	List<SnowFlake> flakes = new ArrayList<>(initialCount);
	private PVector centre;
	PImage image;

	@Override
	public void settings() {
		size(800, 600);
	}

	@Override
	public void setup() {
		setCentre(width / 2, height / 2);
		calculateInitialSnowFlakes();
		// image = loadImage("Snow.jpg");
		// image.resize(800, 600);
	}

	private void calculateInitialSnowFlakes() {
		addFlakes(initialCount);
	}

	@Override
	public void draw() {
		frameRate(23.4f);
		background(0);
		// background(image);
		if (flakes.size() < initialCount) {
			int difference = initialCount - flakes.size();
			addFlakes(difference);
		}
		List<SnowFlake> outOfRange = new ArrayList<>();
		for (SnowFlake flake : flakes) {
			flake.update();
			if (flake.isOutOfRange()) {
				outOfRange.add(flake);
			} else {
				flake.show();
			}
		}
		flakes.removeAll(outOfRange);
		outOfRange = null;
	}

	private void addFlakes(int difference) {
		for (int i = 0; i < difference; i++) {
			SnowFlake flake = new SnowFlake(this);
			flakes.add(flake);
		}
	}

	public PVector getCentre() {
		return centre;
	}

	public void setCentre(int x, int y) {
		this.centre = new PVector(x, y);
	}
}
