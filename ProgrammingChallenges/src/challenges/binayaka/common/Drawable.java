package challenges.binayaka.common;

/**
 * Any object that can be shown on the canvas, will implement this interface
 * 
 * @author Binayaka
 *
 */
public interface Drawable {
	/**
	 * This method should draw the current object on the canvas
	 */
	public void show();

	/**
	 * Any operation that needs to be done on this object prior to drawing it
	 */
	public void update();
}
