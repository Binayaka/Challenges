package challenges.binayaka.miscellaneous;

import java.math.BigInteger;
import java.util.Stack;

/**
 * This was taken from <br/>
 * {@link <a href=
 * "http://rosettacode.org/wiki/Ackermann_function#Java">http://rosettacode.org/wiki/Ackermann_function#Java</a>}
 * 
 * @author Binayaka
 *
 */
public interface Ackermann {
	public static Ackermann new_(BigInteger number1, BigInteger number2, Stack<BigInteger> stack, boolean flag) {
		return $.new_(number1, number2, stack, flag);
	}

	public static void main(String... arguments) {
		$.mainThing(arguments);
	}

	public BigInteger number1();

	public BigInteger number2();

	public Stack<BigInteger> stack();

	public boolean flag();
}
