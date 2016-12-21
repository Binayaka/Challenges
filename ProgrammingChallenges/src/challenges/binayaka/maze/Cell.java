package challenges.binayaka.maze;

import java.util.Arrays;

import challenges.binayaka.common.Drawable;

/**
 * This will denote the individual cell of the maze
 * 
 * @author Binayaka
 *
 */
public class Cell implements Drawable {
	private final MazeMain parent;
	private final int xPos;
	private final int yPos;
	private boolean visited;
	private final Wall[] walls;
	private final Membrane cellMembrane;
	private final int rowIndicator;
	private int visitCount;

	/**
	 * This will create a new cell at the specified grid loc
	 * 
	 * @param app
	 *            The main class, used for drawing on the canvas
	 * @param i
	 * @param j
	 */
	public Cell(MazeMain app, int i, int j) {
		parent = app;
		xPos = i;
		yPos = j;
		visited = false;
		rowIndicator = j;
		cellMembrane = new HexaMembrane(this);
		// cellMembrane = new SquareMembrane(this);
		walls = cellMembrane.defineWalls();
	}

	public int getVisitCount() {
		return visitCount;
	}

	public int getRowIndicator() {
		return rowIndicator;
	}

	public Membrane getCellMembrane() {
		return cellMembrane;
	}

	public MazeMain getCore() {
		return parent;
	}

	public int getxPos() {
		return xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public Wall[] getWalls() {
		return walls;
	}

	/**
	 * Is this cell visited?
	 * 
	 * @return
	 */
	public boolean isVisited() {
		return visited;
	}

	/**
	 * Set the visibility of this cell
	 * 
	 * @param visited
	 */
	public void setVisited(boolean visited) {
		if(visited && this.visited){
			increaseVisitCount();
		}
		this.visited = visited;
	}

	/**
	 * This will increase the visit count
	 */
	public void increaseVisitCount() {
		visitCount++;
	}

	/**
	 * This will highlight this cell
	 */
	public void highLight() {
		cellMembrane.highLight();
	}

	@Override
	public void show() {
		cellMembrane.drawBorders();
		if (this.visited) {
			cellMembrane.fillContents();
		}
	}

	@Override
	public void update() {
		// we have no update operation currently
	}

	public String toString() {
		return "Cell[" + xPos + ",][" + yPos + "] - {" + Arrays.toString(walls) + "}";
	}
}
