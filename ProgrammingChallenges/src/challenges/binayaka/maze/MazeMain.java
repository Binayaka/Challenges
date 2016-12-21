package challenges.binayaka.maze;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

/**
 * This will be our main class for the maze algorithm
 * 
 * @author Binayaka
 *
 */
public class MazeMain extends PApplet {

	int cols, rows;
	int cellWidth = 40;
	List<Cell> grid = new ArrayList<>();
	Cell current = null;
	DepthFirst generationAlgo = new DepthFirst();
	private boolean finished = false;
	private float translatedX = 0f;
	private float translatedY = 0f;

	@Override
	public void settings() {
		size(800, 800);
	}

	@Override
	public void setup() {
		// It turns out that hexagonal grids have to be created a bit
		// differently
		Grid gridType = Grid.HEX;
		// Grid gridType = Grid.SQUARE;
		if (gridType.equals(Grid.HEX)) {
			cols = floor(width / cellWidth) - 1;
			translatedX = ((floor(width / cellWidth) - cols) * cellWidth) / 4;
		} else {
			cols = floor(width / cellWidth);
		}
		rows = floor(height / cellWidth);
		if (gridType.equals(Grid.HEX)) {
			float lastLine = rows * cellWidth;
			float lastActualLine = lastLine - (rows * PApplet.floor(cellWidth / 4));
			float lengthDiff = height - lastActualLine;
			translatedY = lengthDiff / 2;
		}
		for (int j = 0; j < rows; j++) {
			for (int i = 0; i < cols; i++) {
				Cell cell = new Cell(this, i, j);
				grid.add(cell);
			}
		}
		current = grid.get(0);
	}

	@Override
	public void draw() {
		background(51);
		int size = grid.size();
		for (int i = 0; i < size; i++) {
			grid.get(i).show();
		}
		generationAlgo.setCurrent(current);
		generationAlgo.generateMaze();
		current = generationAlgo.getCurrent();
		if (generationAlgo.isCompleted() && !finished) {
			finished = true;
			System.out.println("Completed");
		}
	}

	/**
	 * This will transform the 2D identifier into a 1D index
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public int index(int i, int j) {
		if (i < 0 || j < 0 || i > cols - 1 || j > rows - 1) {
			return -1;
		}
		return i + j * cols;
	}

	/**
	 * This will return a cell at the given pos, if it exists
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public Cell getCell(int i, int j) {
		int index = index(i, j);
		if (index >= 0) {
			return grid.get(index);
		} else {
			return null;
		}
	}

	public float getTranslatedX() {
		return translatedX;
	}

	public float getTranslatedY() {
		return translatedY;
	}

}
