package challenges.binayaka.common;

/**
 * This class will contain all our common methods
 * 
 * @author Binayaka
 *
 */
public class Utils {

	/**
	 * This is taken from the Processing Map method. Check out that library!
	 * <br/>
	 * Re-maps a number from one range to another.
	 * 
	 * In the first example above, the number 25 is converted from a value in
	 * the range of 0 to 100 into a value that ranges from the left edge of the
	 * window (0) to the right edge (width).
	 * 
	 * As shown in the second example, numbers outside of the range are not
	 * clamped to the minimum and maximum parameters values, because
	 * out-of-range values are often intentional and useful.
	 * 
	 * 
	 * 
	 * @param value
	 *            float: the incoming value to be converted
	 * @param istart
	 *            float: lower bound of the value's current range
	 * @param istop
	 *            float: upper bound of the value's current range
	 * @param ostart
	 *            float: lower bound of the value's target range
	 * @param ostop
	 *            float: upper bound of the value's target range
	 * @return float
	 */
	public static final float map(float value, float istart, float istop, float ostart, float ostop) {
		// System.out.println("Mapping " + value + " from " + istart + " to " +
		// istop);
		return ostart + (ostop - ostart) * ((value - istart) / (istop - istart));
	}

}
