package challenges.binayaka.algorithmicBotany;

import java.util.ArrayList;
import java.util.List;

import challenges.binayaka.common.Drawable;
import processing.core.PApplet;
import processing.core.PVector;

/**
 * Every flower will have layers of petals
 * 
 * @author Binayaka
 *
 */
public class Flower implements Drawable {
	private final List<PetalLayer> layers;
	private final PApplet main;
	private final PVector root;

	public Flower(PApplet main_, PVector root_) {
		layers = new ArrayList<>();
		main = main_;
		root = root_;
	}

	public Flower(PApplet main_, PVector root_, float f, float g) {
		layers = new ArrayList<>();
		main = main_;
		root = root_;
	}

	/**
	 * This will add a layer of petals to the flower
	 */
	public void addLayer() {
		generateNewLayer(layers.size() + 1);
	}

	public void removeLastLayer() {
		if (layers.size() > 0) {
			layers.remove(layers.size() - 1);
		}

	}

	/**
	 * This will add a new petalLayer
	 * 
	 * @param generation
	 */
	private void generateNewLayer(int generation) {
		PetalLayer layer = new PetalLayer(main, root, generation);
		layer.generatePetals();
		layers.add(layer);
	}

	@Override
	public void show() {
		for (PetalLayer layer : layers) {
			layer.drawLayer();
		}
	}

	@Override
	public void update() {

	}

	public void addPetalLayer() {
		// TODO Auto-generated method stub

	}

}
