package challenges.binayaka.miscellaneous;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Naive ackermann implementation
 * 
 * @author Binayaka
 *
 */
public class SimpleAckerman {

	class Pair {
		final BigInteger a, b;

		public Pair(BigInteger m, BigInteger n) {
			a = m;
			b = n;
		}

		public BigInteger getA() {
			return a;
		}

		public BigInteger getB() {
			return b;
		}

		@Override
		public int hashCode() {
			return a.intValue() * 31 + b.intValue() * 31;
		}

		@Override
		public boolean equals(Object that) {
			if (that == this) {
				return true;
			} else if (that.getClass().equals(this.getClass())) {
				Pair o = (Pair) that;
				if (o.getA().compareTo(this.getA()) == 0 && o.getB().compareTo(this.getB()) == 0) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}

		@Override
		public String toString() {
			return "(" + a + "," + b + ")";
		}
	}

	private static final Map<Pair, BigInteger> map = new HashMap<>();

	public static void main(String[] args) {
		SimpleAckerman man = new SimpleAckerman(); // not very subtle, am I?
		man.run();
	}

	public void run() {
		System.out.println("Testing ackerman function");
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				BigInteger ackerman = ackerman(BigInteger.valueOf(i), BigInteger.valueOf(j));
				System.out.println("Ackerman(" + i + "," + j + ") :: " + ackerman.intValue());
			}
		}
	}

	/**
	 * This will compute the Ackermann method
	 * 
	 * @param m
	 * @param n
	 */
	public BigInteger ackerman(BigInteger m, BigInteger n) {
		try {
			if (m.compareTo(BigInteger.ZERO) < 0 && n.compareTo(BigInteger.ZERO) < 0) {
				return BigInteger.ZERO;
			} else {
				Pair val = new Pair(m, n);
				if (map.get(val) == null) {
					// System.err.println("Running for " + m + "," + n);
					if (m.compareTo(BigInteger.ZERO) == 0) {
						BigInteger n1 = n.add(BigInteger.ONE);
						map.put(val, n1);
						return n1;
					} else if (m.compareTo(BigInteger.ZERO) > 0 && n.compareTo(BigInteger.ZERO) == 0) {
						BigInteger m_1 = m.subtract(BigInteger.ONE);
						BigInteger res = ackerman(m_1, BigInteger.ONE);
						map.put(val, res);
						return res;
					} else if (m.compareTo(BigInteger.ZERO) > 0 && n.compareTo(BigInteger.ZERO) > 0) {
						BigInteger m_1 = m.subtract(BigInteger.ONE);
						BigInteger n_1 = n.subtract(BigInteger.ONE);
						BigInteger res = ackerman(m_1, ackerman(m, n_1));
						map.put(val, res);
						return res;
					} else {
						map.put(val, BigInteger.ZERO);
						return BigInteger.ZERO;
					}
				} else {
					return map.get(val);
				}
			}
		} catch (Throwable e) {
			System.out.println("Error for (" + m + "," + n + ")\n" + e.getMessage());
			// e.printStackTrace();
		}
		return BigInteger.valueOf(0);
	}

}
