package challenges.binayaka.maze;

import java.util.Stack;

/**
 * This will generate a maze using the DFS algo
 * 
 * @author Binayaka
 *
 */
public class DepthFirst implements GenerateAlgorithms {

	private Cell current;
	Stack<Cell> backTrack = new Stack<>();
	private boolean completed = false;

	public boolean isCompleted() {
		return completed;
	}

	public Cell getCurrent() {
		return current;
	}

	public void setCurrent(Cell current) {
		this.current = current;
	}

	@Override
	public void generateMaze() {
		current.setVisited(true);
		current.highLight();
		Cell next = current.getCellMembrane().checkNeighbours();
		if (next != null) {
			next.setVisited(true);
			backTrack.push(current);
			current.getCellMembrane().removeWalls(current, next);
			current = next;
		} else if (backTrack.size() > 0) {
			current = backTrack.pop();
		} else {
			completed = true;
		}
	}
}
