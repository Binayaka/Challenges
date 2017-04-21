package challenges.binayaka.walker;

import processing.core.PApplet;

public class RandomWalker extends PApplet {
	Walker walker;

	@Override
	public void settings() {
		size(800, 600);
	}

	@Override
	public void setup() {
		background(0);
		walker = new Walker(this);
	}

	@Override
	public void draw() {
		translate(width / 2, height / 2);
		walker.update();
		walker.show();
	}

}
