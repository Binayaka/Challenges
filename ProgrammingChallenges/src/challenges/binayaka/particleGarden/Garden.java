package challenges.binayaka.particleGarden;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

/**
 * This will show the particle garden
 * 
 * @author Binayaka
 *
 */
public class Garden extends PApplet {

	int attractionDist = 100;
	int repulsionDist = 50;
	int G = 50;

	List<Particle> particles = new ArrayList<>();
	int PARTICLE_COUNT = 30;

	@Override
	public void settings() {
		size(800, 600);
	}

	@Override
	public void setup() {
		for (int i = 0; i < PARTICLE_COUNT; i++) {
			particles.add(new Particle(this));
		}
	}

	@Override
	public void draw() {
		background(0);
		strokeWeight(1);

		for (Particle p : particles) {
			p.update();
			p.show();
			p.displayStats();
		}

		for (Particle p : particles) {
			for (Particle other : particles) {
				if (!other.equals(p)) {
					float distance = dist(p.pos.x, p.pos.y, other.pos.x, other.pos.y);
					if (distance < attractionDist && distance > repulsionDist) {
						// i.e., the two particles are within range
						float strokeWidth = map(distance, 0, attractionDist, 1, 2);
						float strokeAlpha = map(distance, 0, attractionDist, 255, 10);
						stroke(255, strokeAlpha);
						strokeWeight(strokeWidth);
						line(p.pos.x, p.pos.y, other.pos.x, other.pos.y);
						p.attracted(other.pos);
						other.attracted(p.pos);
					}
					else if(distance <= repulsionDist) {
						float strokeWidth = map(distance, 0, attractionDist, 1, 2);
						float strokeAlpha = map(distance, 0, attractionDist, 255, 10);
						stroke(255, strokeAlpha);
						strokeWeight(strokeWidth);
						line(p.pos.x, p.pos.y, other.pos.x, other.pos.y);
						p.repulse(other.pos);
						other.repulse(p.pos);
					}
				}
			}
		}
	}
}
