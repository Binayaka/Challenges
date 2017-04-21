package challenges.binayaka.matrix;

import processing.core.PApplet;

public class MatrixDisplay extends PApplet implements MatrixDisplayConstants {

	Symbol sym;

	@Override
	public void settings() {
		size(800, 600);
	}

	@Override
	public void setup() {
		textSize(textSize);
		fill(255);
		sym = new Symbol(this, width / 2, height / 2, 3);
	}

	@Override
	public void draw() {
		background(0);
		sym.update();
		sym.show();
	}

}
