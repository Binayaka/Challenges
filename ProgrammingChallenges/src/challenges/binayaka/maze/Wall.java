package challenges.binayaka.maze;

/**
 * This will identify a wall in our cells
 * 
 * @author Binayaka
 *
 */
public class Wall {

	private boolean state;

	public Wall(boolean val) {
		state = val;
	}

	public boolean getState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return state + "";
	}

}
