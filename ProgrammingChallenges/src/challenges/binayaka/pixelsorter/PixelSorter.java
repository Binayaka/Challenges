package challenges.binayaka.pixelsorter;

import java.util.Comparator;

import challenges.binayaka.common.IterativeIntegerMergeSort;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * This will try to implement a pixel sorting algo
 * 
 * @author Binayaka
 *
 */
public class PixelSorter extends PApplet {
	PImage jelly;
	PImage sorted;
	IterativeIntegerMergeSort sorter;
	int pHeight = 450;	
	int individualWidth = 800;
	int pWidth = individualWidth * 2;

	@Override
	public void settings() {
		size(pWidth, pHeight);
	}

	@Override
	public void setup() {
		jelly = loadImage("image.jpg");
		jelly.resize(individualWidth, pHeight);
		sorted = jelly.get();
		sorted.loadPixels();
		sorter = new IterativeIntegerMergeSort();
		sorter.initializeParameters(sorted.pixels);
		sorter.initializeCompator(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				Float thisVal = Float.valueOf(hue(o1));
				Float thatVal = Float.valueOf(hue(o2));
				return thisVal.compareTo(thatVal);
			}
		});
		sorter.executeSort();
	}

	@Override
	public void draw() {
		background(0);
		image(jelly, 0, 0);
		image(sorted, 800, 0);
		sorted.updatePixels();
	}

}
