package challenges.binayaka.common;

import java.util.HashMap;
import java.util.Map;

/**
 * This will be used to implement multi-ton architecture
 * 
 * @author Binayaka
 *
 */
public class InstansceKeeper {

	@SuppressWarnings("rawtypes")
	private static Map<Class, Object> map = new HashMap<>();

	/**
	 * This will register the instance of the class
	 */
	@SuppressWarnings("rawtypes")
	public static void registerInstance(Class c, Object n) {
		map.put(c, n);
	}

	@SuppressWarnings("rawtypes")
	public static Object getInstance(Class c) {
		return map.get(c);
	}

}
