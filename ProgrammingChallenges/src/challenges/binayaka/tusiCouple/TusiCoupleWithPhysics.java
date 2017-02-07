package challenges.binayaka.tusiCouple;

import java.util.ArrayList;
import java.util.List;

import fisica.FCircle;
import fisica.FConstantVolumeJoint;
import fisica.FWorld;
import fisica.Fisica;
import processing.core.PApplet;

/**
 * We will try to do this with toxic libs
 * 
 * @author Binayaka
 *
 */
public class TusiCoupleWithPhysics extends PApplet {

	FWorld world;
	int N = 50;
	int SMALLER_CIRCLES = 50;
	List<FCircle> circles;

	@Override
	public void settings() {
		size(800, 800);
	}

	@Override
	public void setup() {
		colorMode(RGB);
		smooth();
		Fisica.init(this);
		world = new FWorld();
		world.setGravity(0, 0);
		world.setEdges();
		FConstantVolumeJoint joint = new FConstantVolumeJoint();
		for (int i = 0; i < N; i++) {
			FCircle circle = new FCircle(10);
			circle.setFill(255, 100, 100);
			circle.setPosition(width / 2 + 100 * cos(radians(i * 360 / N)),
					height / 2 + 100 * sin(radians(i * 360 / N)));
			circle.setNoFill();
			circle.setNoStroke();
			joint.addBody(circle);
			world.add(circle);
		}
		joint.setNoFill();
		joint.setStrokeWeight(5);
		joint.setStroke(255, 0, 50);
		world.add(joint);

		circles = new ArrayList<>();
		for (int i = 0; i < SMALLER_CIRCLES; i++) {
			FCircle c = new FCircle(random(15, 30));
			c.setPosition(width / 2 + random(-50, 50), height / 2 + random(-50, 50));
			c.setFill(100 + random(155), 150, random(255));
			c.setNoStroke();
			circles.add(c);
			world.add(c);
		}
	}

	@Override
	public void draw() {
		background(0);
		for (int i = 0; i < circles.size(); i++) {
			FCircle c = (FCircle) circles.get(i);
			c.addForce(random(-200, 200), random(-200, 200));
		}
		world.step();
		world.draw(this);
	}
}
