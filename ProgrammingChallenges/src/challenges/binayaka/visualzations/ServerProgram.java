package challenges.binayaka.visualzations;

import java.io.IOException;

/**
 * This will generate the info about the program, and transmit it across the
 * network
 * 
 * @author Binayaka
 *
 */
public class ServerProgram {
	public static void main(String[] args) throws IOException {
		StatGeneratorThread thread = new StatGeneratorThread();
		thread.start();
	}
}
