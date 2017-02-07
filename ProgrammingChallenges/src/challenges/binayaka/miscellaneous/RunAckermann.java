package challenges.binayaka.miscellaneous;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class RunAckermann {
	static BigInteger ZERO = BigInteger.ZERO;
	static BigInteger ONE = BigInteger.ONE;
	static BigInteger TWO = BigInteger.valueOf(2);
	static BigInteger THREE = BigInteger.valueOf(3);

	public static void main(String[] args) {

		/*
		 * This logic will make sure that the file gets created if it is not
		 * present at the specified location
		 */
		BufferedWriter bw = null;
		try {
			File file = new File("val2.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			String value = Ackermann(BigInteger.valueOf(4), BigInteger.valueOf(3)).toString();
			bw.write(value);
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
			bw = null;
		} finally {
			try {
				if (bw != null)
					bw.close();
			} catch (Exception ex) {
				System.out.println("Error in closing the BufferedWriter" + ex);
			}
		}
		System.out.println("Finished!");
	}

	public static BigInteger Ackermann(BigInteger m, BigInteger n) {
		OverflowlessStack<BigInteger> stack = new OverflowlessStack<BigInteger>();
		stack.Push(m);
		boolean shouldPop = true;
		while (!stack.IsEmpty()) {
			if (shouldPop) {
				m = stack.Pop();
			}
			if (m.compareTo(ZERO) == 0) {
				n = n.add(ONE);
			} else if (m.compareTo(ONE) == 0) {
				n = n.add(TWO);
			} else if (m.compareTo(TWO) == 0) {
				n = n.multiply(TWO).add(THREE);
			} else if (n.compareTo(ZERO) == 0) {
				m = m.subtract(ONE);
				n = ONE;
				shouldPop = false;
				continue;
			} else {
				stack.Push(m.subtract(ONE));
				n = n.subtract(ONE);
				shouldPop = false;
				continue;
			}
			shouldPop = true;
		}
		return n;
	}

}
