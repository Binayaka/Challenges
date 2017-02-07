package challenges.binayaka.genericAlgos.flowField;

/**
 * This will store all our constants for the flow field
 * 
 * @author Binayaka
 *
 */
public interface FlowFieldConstants {

	/**
	 * Scale of grid is 1/24 of screen size. Currently {@value #gridScale}
	 */
	public final int gridScale = 10;

	/**
	 * The max speed for the rocket
	 */
	public final float maxspeed = 6.0f;

	/**
	 * The max force for the rocket
	 */
	public final float maxforce = 1.0f;

	/**
	 * The initial starting value for the finish param
	 */
	public final int INIT_MAX_FINISH_VAL = Integer.MAX_VALUE;

	/**
	 * The size of target
	 */
	public final int diam = 24;

}
