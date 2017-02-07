package challenges.binayaka.algorithmicBotany;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * This will hold all the petals of a layer
 * 
 * @author Binayaka
 *
 */
public class PetalLayer {

	private List<Petal> petals;
	private final PApplet main;
	private final PVector root;
	private final int generation;
	private float radius = 20f;
	private float petalLength = 200;
	private final Map<Integer, Petal> map;

	public PetalLayer(PApplet main_, PVector root_, int generation_) {
		main = main_;
		root = root_;
		generation = generation_;
		map = new HashMap<>();
	}

	public void generatePetals() {
		if (generation < 1) {
			return;
		}
		// float val = (360 / PApplet.pow(4, generation));
		int totalPetals = PApplet.ceil(PApplet.pow(2, 2 + (generation - 1)));
		petals = new ArrayList<>(totalPetals);
		// reset starting angle to 0 degrees
		float angle = 0.0f;
		float pX, pY;
		// draw petals around the center ellipse of the flower
		radius = radius + (generation * 10);
		petalLength = petalLength + (generation * 40);
		for (int c = 0; c < totalPetals; c++) {
			// calculate starting point
			float abs = PApplet.abs(angle);
			if (map.containsKey(Integer.valueOf(c))) {
				continue;
			}
			int compVal = PApplet.floor(abs);
			pX = root.x + PApplet.cos(PApplet.radians(angle)) * 20;
			pY = root.y + PApplet.sin(PApplet.radians(angle)) * 20;
			if (compVal >= 0 && compVal < 90) {
			} else if (compVal >= 90 && compVal < 180) {
				pY = pY * -1;
			} else if (compVal >= 180 && compVal < 270) {
			} else if (compVal >= 270 && compVal < 360) {
				pY = pY * -1;
			}
			Petal p = addPetal(pX, pY, abs);
			// call drawPetal function to calculate control points and set
			// bezierVertices
			// increment angle based on total number of petals
			angle -= (360 / totalPetals);
			map.put(Integer.valueOf(c), p);
		}
	}

	private Petal addPetal(float pX, float pY, float abs) {
		Petal petal = new Petal(main, pX, pY, abs, petalLength, generation);
		// int ceil = PApplet.ceil(PApplet.map(abs, 0, 360, 100, 255));
		// petal.setColor(ceil);
		petals.add(petal);
		return petal;
	}

	public void drawLayer() {
		for (Petal petal : petals) {
			petal.show();
		}
	}
}
