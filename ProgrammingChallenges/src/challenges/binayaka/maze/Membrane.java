package challenges.binayaka.maze;

/**
 * Every cell has a membrane. This will define the shape of the cell, as well as
 * the checkNeighbours method, used for determining the next cell
 * 
 * @author Binayaka
 *
 */
public interface Membrane {
	/**
	 * This will draw the actual borders of the cell.
	 */
	public void drawBorders();

	/**
	 * This will fill the cell
	 */
	public void fillContents();

	/**
	 * This will highlight the cell
	 */
	public void highLight();

	/**
	 * This will return the next cell for algorithms
	 * 
	 * @return
	 */
	public Cell checkNeighbours();

	/**
	 * This will determine the amount of walls for this membrane
	 * 
	 * @return
	 */
	public Wall[] defineWalls();

	/**
	 * This will remove the walls based on the edges
	 * 
	 * @param a
	 * @param b
	 */
	public void removeWalls(Cell a, Cell b);
}
