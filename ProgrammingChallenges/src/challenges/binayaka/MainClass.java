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
		System.setProperty("fromEclipse", "true");
		runBeatAnalyzer();
	}

	public static void prev() {
		// runMaze();
		// runTest();
		// runPixelSorter();
		// runPerlinNoise3D();
		// runMandelBrot();
		// runJulia();
		// runLSystem();
		// runSpaceColonization();
		// runCirclePackaging();
		// runCirclePackagingImage();
		// runPhyllotaxis();
		// runLorenzAttractor();
		// runSineWave3D();
		// runFlower();
		// runTusiCouple(); <-- this needs more work
		// runEvolvingFlowField();
	}

	public static void runBeatAnalyzer() {
		PApplet.main("challenges.binayaka.sound.BeatAnalyzer");
	}

	public static void runEvolvingFlowField() {
		PApplet.main("challenges.binayaka.genericAlgos.flowField.EvolvingFlowField");
	}

	public static void runTusiCouple() {
		PApplet.main("challenges.binayaka.tusiCouple.TusiCoupleWithPhysics");
	}

	public static void runFlower() {
		PApplet.main("challenges.binayaka.algorithmicBotany.MainFlower");
	}

	public static void runSineWave3D() {
		PApplet.main("challenges.binayaka.waves.SineWave3D");
	}

	public static void runLorenzAttractor() {
		PApplet.main("challenges.binayaka.attractors.LorenzAttractor");
	}

	public static void runPhyllotaxis() {
		PApplet.main("challenges.binayaka.algorithmicBotany.Phyllotaxis");
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

	/**
	 * This will run our pixel sorter class
	 */
	public static void runPixelSorter() {
		PApplet.main("challenges.binayaka.pixelsorter.PixelSorter");
	}

	/**
	 * This will run our perlin noise terrain generator
	 */
	public static void runPerlinNoise3D() {
		PApplet.main("challenges.binayaka.perlin.Perlin3D");
	}

	/**
	 * This will run our mandelbrot set
	 */
	public static void runMandelBrot() {
		PApplet.main("challenges.binayaka.fractal.Mandelbrot");
	}

	/**
	 * This will run our julia set
	 */
	public static void runJulia() {
		PApplet.main("challenges.binayaka.fractal.Julia");
	}

	/**
	 * This will run our L-System generator
	 */
	public static void runLSystem() {
		PApplet.main("challenges.binayaka.trees.LSystem");
	}

	/**
	 * This will run the 2D space colonization
	 */
	public static void runSpaceColonization() {
		PApplet.main("challenges.binayaka.trees.fractalTrees.SpaceColonization");
	}

	/**
	 * This will run the circle packaging algo
	 */
	public static void runCirclePackaging() {
		PApplet.main("challenges.binayaka.circlePacking.CirclePacking");
	}

	/**
	 * This will run the circle packaging algo
	 */
	public static void runCirclePackagingImage() {
		PApplet.main("challenges.binayaka.circlePacking.CirclePackingImage");
	}
}
