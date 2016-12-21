package challenges.binayaka;

import processing.core.PApplet;

/**
 * This will launch our current challenge program
 * 
 * @author Binayaka
 *
 */
public class MainClass {

	/**
	 * Use this to call the actual running class
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		runMaze();
		// runTest();
	}

	/**
	 * This will run our maze program
	 */
	public static void runMaze() {
		PApplet.main("challenges.binayaka.maze.MazeMain");
	}

	/**
	 * This will run our test class
	 */
	public static void runTest() {
		PApplet.main("challenges.binayaka.common.Test");
	}
}
