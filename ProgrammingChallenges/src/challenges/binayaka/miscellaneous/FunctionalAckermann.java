package challenges.binayaka.miscellaneous;

import java.math.BigInteger;
import java.util.Stack;

@FunctionalInterface
public interface FunctionalAckermann extends FunctionalField<Field>, Ackermann {
	@Override
	public default BigInteger number1() {
		return field(Field.number1);
	}

	@Override
	public default BigInteger number2() {
		return field(Field.number2);
	}

	@Override
	public default Stack<BigInteger> stack() {
		return field(Field.stack);
	}

	@Override
	public default boolean flag() {
		return field(Field.flag);
	}
}
