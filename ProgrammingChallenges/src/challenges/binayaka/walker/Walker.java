package challenges.binayaka.walker;

import challenges.binayaka.common.Drawable;
import processing.core.PApplet;
import processing.core.PVector;

public class Walker implements Drawable {
	PApplet main;
	PVector pos;
	PVector next;
	int maxMovement = 10;
	int minMovement = 2;

	public Walker(PApplet _parent) {
		main = _parent;
		pos = new PVector(0, 0);
		next = pos;
	}

	@Override
	public void show() {
		PVector prev = pos;
		pos.add(next);
		if (pos.x <= -main.width / 2) {
			pos.x = main.width / 2;
		} else if (pos.x >= main.width / 2) {
			pos.x = -main.width / 2;
		}
		if (pos.y <= -main.height / 2) {
			pos.y = main.height / 2;
		} else if (pos.y >= main.height / 2) {
			pos.y = -main.height / 2;
		}
		main.stroke(255);
		main.line(pos.x, pos.y, prev.x, prev.y);
	}

	@Override
	public void update() {
		float val = main.random(0, 1);
		Directions nextDirection = calculateDirection(val);
		int xSign = 1;
		int ySign = 1;
		switch (nextDirection) {
		case NORTH:
			xSign = 0;
			ySign = 1;
			break;
		case SOUTH:
			xSign = 0;
			ySign = -1;
			break;
		case EAST:
			xSign = 1;
			ySign = 0;
			break;
		case WEST:
			xSign = -1;
			ySign = 0;
			break;
		case NORTH_EAST:
			xSign = 1;
			ySign = 1;
			break;
		case SOUTH_EAST:
			xSign = 1;
			ySign = -1;
			break;
		case NORTH_WEST:
			xSign = -1;
			ySign = 1;
			break;
		case SOUTH_WEST:
			xSign = -1;
			ySign = -1;
			break;
		}
		float xPos = main.random(minMovement, maxMovement) * xSign;
		float yPos = main.random(minMovement, maxMovement) * ySign;
		next = new PVector(xPos, yPos);
	}

	private Directions calculateDirection(float val) {
		if (val <= 0.12) {
			return Directions.NORTH;
		} else if (val <= 0.24) {
			return Directions.SOUTH;
		} else if (val <= 0.36) {
			return Directions.EAST;
		} else if (val <= 0.48) {
			return Directions.WEST;
		} else if (val <= 0.60) {
			return Directions.NORTH_EAST;
		} else if (val <= 0.72) {
			return Directions.SOUTH_EAST;
		} else if (val <= 0.84) {
			return Directions.NORTH_WEST;
		} else {
			return Directions.SOUTH_WEST;
		}
	}

}
