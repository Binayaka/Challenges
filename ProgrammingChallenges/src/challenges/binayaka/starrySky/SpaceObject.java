package challenges.binayaka.starrySky;

import challenges.binayaka.common.Drawable;

/**
 * This will mark our space objects
 * 
 * @author Binayaka
 *
 */
public interface SpaceObject extends Drawable {
	final int MIN_PLANET_SIZE = 1;
	final int MAX_PLANET_SIZE = 3;
	final int MIN_STAR_SIZE = 1;
	final int MAX_STAR_SIZE = 7;
	final int STAR_MIN_BRIGHTNESS = 0;
	final int STAR_MAX_BRIGHTNESS = 255;
	final float GROWTH_FACTOR = 0.2f;
	final float PLANET_BRIGHTNESS = 100;
}
