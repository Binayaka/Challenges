package challenges.binayaka.genericAlgos.flowField;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * This will designate the DNA of the generic algorithms Flow field example
 * 
 * @author Binayaka
 *
 */
public class DNA implements FlowFieldConstants {

	PVector[] genes;
	private final PApplet main;

	// Constructor (makes a DNA of random PVectors)
	public DNA(PApplet core, int num) {
		main = core;
		genes = new PVector[num];
		for (int i = 0; i < genes.length; i++) {
			genes[i] = PVector.random2D();
		}
	}

	// Constructor #2, creates the instance based on an existing array
	public DNA(PApplet core, PVector[] newgenes) {
		// We could make a copy if necessary
		// genes = (PVector []) newgenes.clone();
		genes = newgenes;
		main = core;
	}

	// CROSSOVER
	// Creates new DNA sequence from two (this & and a partner)
	public DNA crossover(DNA partner) {
		PVector[] child = new PVector[genes.length];
		// Pick a midpoint
		int crossover = (int) main.random(genes.length);
		// Take "half" from one and "half" from the other
		for (int i = 0; i < genes.length; i++) {
			if (i > crossover)
				child[i] = genes[i];
			else
				child[i] = partner.genes[i];
		}
		DNA newgenes = new DNA(main, child);
		return newgenes;
	}

	// Based on a mutation probability, picks a new random Vector
	public void mutate(float m) {
		for (int i = 0; i < genes.length; i++) {
			if (main.random(1) < m) {
				genes[i] = PVector.random2D();
			}
		}
	}

	public void debugDraw() {
		int cols = main.width / gridScale;
		int rows = main.height / gridScale;
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				drawVector(genes[i + j * cols], i * gridScale, j * gridScale, gridScale - 2);
			}
		}
	}

	// Renders a vector object 'v' as an arrow and a position 'x,y'
	public void drawVector(PVector v, float x, float y, float scayl) {
		main.pushMatrix();
		// float arrowsize = 4;
		// Translate to position to render vector
		main.translate(x + gridScale / 2, y);
		main.stroke(0, 100);
		// Call vector heading function to get direction (note that pointing up
		// is a heading of 0) and rotate
		main.rotate(v.heading());
		// Calculate length of vector & scale it to be bigger or smaller if
		// necessary
		float len = v.mag() * scayl;
		// Draw three lines to make an arrow (draw pointing up since we've
		// rotate to the proper direction)
		main.line(-len / 2, 0, len / 2, 0);
		// noFill();
		// ellipse(-len/2,0,2,2);
		main.popMatrix();
	}

}
