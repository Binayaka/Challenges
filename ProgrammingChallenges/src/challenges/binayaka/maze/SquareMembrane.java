package challenges.binayaka.maze;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

/**
 * This defines a 4 celled square type membrane
 * 
 * @author Binayaka
 *
 */
public class SquareMembrane implements Membrane {
	private Cell parent;

	public SquareMembrane(Cell core) {
		parent = core;
	}

	@Override
	public void drawBorders() {
		// 0, 1, 2, 3
		// top, right, bottom, left
		int cellWidth = this.parent.getCore().cellWidth;
		int x = this.parent.getxPos() * cellWidth;
		int y = this.parent.getyPos() * cellWidth;
		this.parent.getCore().stroke(255);
		// top
		if (this.parent.getWalls()[0].getState()) {
			this.parent.getCore().line(x, y, x + cellWidth, y);
		}
		// right
		if (this.parent.getWalls()[1].getState()) {
			this.parent.getCore().line(x + cellWidth, y, x + cellWidth, y + cellWidth);
		}
		// bottom
		if (this.parent.getWalls()[2].getState()) {
			this.parent.getCore().line(x + cellWidth, y + cellWidth, x, y + cellWidth);
		}
		// left
		if (this.parent.getWalls()[3].getState()) {
			this.parent.getCore().line(x, y + cellWidth, x, y);
		}
	}

	@Override
	public void fillContents() {
		int cellWidth = this.parent.getCore().cellWidth;
		int x = this.parent.getxPos() * cellWidth;
		int y = this.parent.getyPos() * cellWidth;
		parent.getCore().noStroke();
		parent.getCore().fill(255, 0, 255, 100);
		parent.getCore().rect(x, y, cellWidth, cellWidth);
	}

	@Override
	public void highLight() {
		int cellWidth = this.parent.getCore().cellWidth;
		int x = this.parent.getxPos() * cellWidth;
		int y = this.parent.getyPos() * cellWidth;
		parent.getCore().noStroke();
		parent.getCore().fill(0, 0, 255, 100);
		parent.getCore().rect(x, y, cellWidth, cellWidth);
	}

	/**
	 * This will return the nearest neighbour to this cell, if any is available
	 * 
	 * @return
	 */
	@Override
	public Cell checkNeighbours() {
		List<Cell> neighbours = new ArrayList<>(4);
		Cell top = this.parent.getCore().getCell(this.parent.getxPos(), this.parent.getyPos() - 1);
		Cell right = this.parent.getCore().getCell(this.parent.getxPos() + 1, this.parent.getyPos());
		Cell bottom = this.parent.getCore().getCell(this.parent.getxPos(), this.parent.getyPos() + 1);
		Cell left = this.parent.getCore().getCell(this.parent.getxPos() - 1, this.parent.getyPos());
		if (top != null && !top.isVisited()) {
			neighbours.add(top);
		}
		if (right != null && !right.isVisited()) {
			neighbours.add(right);
		}
		if (bottom != null && !bottom.isVisited()) {
			neighbours.add(bottom);
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
		Wall top = new Wall(true);
		Wall right = new Wall(true);
		Wall bottom = new Wall(true);
		Wall left = new Wall(true);
		return new Wall[] { top, right, bottom, left };
	}

	@Override
	public void removeWalls(Cell a, Cell b) {
		int x = a.getxPos() - b.getxPos();
		if (x == 1) {
			a.getWalls()[3].setState(false);
			b.getWalls()[1].setState(false);
		} else if (x == -1) {
			a.getWalls()[1].setState(false);
			b.getWalls()[3].setState(false);
		}
		int y = a.getyPos() - b.getyPos();
		if (y == 1) {
			a.getWalls()[0].setState(false);
			b.getWalls()[2].setState(false);
		} else if (y == -1) {
			a.getWalls()[2].setState(false);
			b.getWalls()[0].setState(false);
		}
	}

}
