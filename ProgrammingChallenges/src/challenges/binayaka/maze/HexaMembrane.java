package challenges.binayaka.maze;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PConstants;

/**
 * This will attempt to create a hexagonal membrane around the cell
 * 
 * @author Binayaka
 *
 */
public class HexaMembrane implements Membrane {

	private Cell parent;

	public HexaMembrane(Cell core) {
		parent = core;
	}

	@Override
	public void drawBorders() {
		int cellWidth = this.parent.getCore().cellWidth;
		int x = this.parent.getxPos() * cellWidth;
		int y = this.parent.getyPos() * cellWidth;
		y = y - (this.parent.getyPos() * PApplet.floor(cellWidth / 4));
		this.parent.getCore().stroke(255);
		y += this.parent.getCore().getTranslatedY();
		if (this.parent.getRowIndicator() % 2 != 0) {
			x += PApplet.floor(cellWidth / 2);
		}
		x += this.parent.getCore().getTranslatedX();
		float c = PApplet.floor(cellWidth / 1);
		float c2 = PApplet.floor(cellWidth / 2);
		float c4 = PApplet.floor(cellWidth / 4);

		float halfX = x + c2;
		float maxX = x + c;
		float quarterY = y + c4;
		float threeFourthY = quarterY + c2;
		float maxY = y + c;

		// HLT
		if (this.parent.getWalls()[0].getState()) {
			this.parent.getCore().line(x, quarterY, halfX, y);
		}
		// HRT
		if (this.parent.getWalls()[1].getState()) {
			this.parent.getCore().line(halfX, y, maxX, quarterY);
		}
		// R
		if (this.parent.getWalls()[2].getState()) {
			this.parent.getCore().line(maxX, quarterY, maxX, threeFourthY);
		}
		// HRB
		if (this.parent.getWalls()[3].getState()) {
			this.parent.getCore().line(maxX, threeFourthY, halfX, maxY);
		}
		// HLB
		if (this.parent.getWalls()[4].getState()) {
			this.parent.getCore().line(halfX, maxY, x, threeFourthY);
		}
		// L
		if (this.parent.getWalls()[5].getState()) {
			this.parent.getCore().line(x, threeFourthY, x, quarterY);
		}
	}

	@Override
	public void fillContents() {
		parent.getCore().noStroke();
		if (parent.getVisitCount() > 1) {
			parent.getCore().fill(85, 244, 96, 100);
		} else {
			parent.getCore().fill(199, 55, 252, 100);
		}
		drawHexagon();

	}

	private void drawHexagon() {
		int cellWidth = this.parent.getCore().cellWidth;
		int x = this.parent.getxPos() * cellWidth;
		int y = this.parent.getyPos() * cellWidth;
		y = y - (this.parent.getyPos() * PApplet.floor(cellWidth / 4));
		parent.getCore().noStroke();
		y += this.parent.getCore().getTranslatedY();
		if (this.parent.getRowIndicator() % 2 != 0) {
			x += PApplet.floor(cellWidth / 2);
		}
		x += this.parent.getCore().getTranslatedX();
		float c = PApplet.floor(cellWidth / 1);
		float c2 = PApplet.floor(cellWidth / 2);
		float c4 = PApplet.floor(cellWidth / 4);

		float halfX = x + c2;
		float maxX = x + c;
		float quarterY = y + c4;
		float threeFourthY = quarterY + c2;
		float maxY = y + c;

		this.parent.getCore().beginShape();
		this.parent.getCore().vertex(x, y + c4);
		this.parent.getCore().vertex(halfX, y);
		this.parent.getCore().vertex(maxX, quarterY);
		this.parent.getCore().vertex(maxX, threeFourthY);
		this.parent.getCore().vertex(halfX, maxY);
		this.parent.getCore().vertex(x, threeFourthY); //
		this.parent.getCore().vertex(x, c4);
		this.parent.getCore().endShape(PConstants.CLOSE);
	}

	@Override
	public void highLight() {
		parent.getCore().noStroke();
		parent.getCore().fill(0, 0, 255, 100);
		drawHexagon();
	}

	@Override
	public Cell checkNeighbours() {
		List<Cell> neighbours = new ArrayList<>(6);
		Cell halfLeftTop = this.parent.getCore().getCell(this.parent.getxPos() - 1, this.parent.getyPos() - 1);
		Cell halfRightTop = this.parent.getCore().getCell(this.parent.getxPos(), this.parent.getyPos() - 1);
		Cell right = this.parent.getCore().getCell(this.parent.getxPos() + 1, this.parent.getyPos());
		Cell left = this.parent.getCore().getCell(this.parent.getxPos() - 1, this.parent.getyPos());
		Cell halfLeftBottom = this.parent.getCore().getCell(this.parent.getxPos() - 1, this.parent.getyPos() + 1);
		Cell halfRightBottom = this.parent.getCore().getCell(this.parent.getxPos(), this.parent.getyPos() + 1);
		if (halfLeftTop != null && !halfLeftTop.isVisited()) {
			neighbours.add(halfLeftTop);
		}
		if (halfRightTop != null && !halfRightTop.isVisited()) {
			neighbours.add(halfRightTop);
		}
		if (right != null && !right.isVisited()) {
			neighbours.add(right);
		}
		if (halfLeftBottom != null && !halfLeftBottom.isVisited()) {
			neighbours.add(halfLeftBottom);
		}
		if (halfRightBottom != null && !halfRightBottom.isVisited()) {
			neighbours.add(halfRightBottom);
		}
		if (left != null && !left.isVisited()) {
			neighbours.add(left);
		}

		if (neighbours.size() > 0) {
			int index = PApplet.floor(this.parent.getCore().random(0, neighbours.size()));
			return neighbours.get(index);
		} else {
			return null;
		}
	}

	@Override
	public Wall[] defineWalls() {
		Wall halfLeftTop = new Wall(true);
		Wall halfRightTop = new Wall(true);
		Wall right = new Wall(true);
		Wall left = new Wall(true);
		Wall halfLeftBottom = new Wall(true);
		Wall halfRightBottom = new Wall(true);
		return new Wall[] { halfLeftTop, halfRightTop, right, halfRightBottom, halfLeftBottom, left };
	}

	@Override
	public void removeWalls(Cell a, Cell b) {

	}

}
