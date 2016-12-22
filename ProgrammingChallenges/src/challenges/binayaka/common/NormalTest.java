package challenges.binayaka.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class NormalTest {

	public static void main(String[] args) {
		// int length = 10;
		int length = 100000;
		//int length = 10000000;
		Integer[] arr = new Integer[length];
		for (int i = 0; i < length; i++) {
			arr[i] = (int) (Math.random() * length);
		}
		IterativeMergeSort sorter = new IterativeMergeSort();
		sorter.initializeParameters(arr);
		long startTime = System.currentTimeMillis();
		sorter.executeSort();
		while (!sorter.isCompleted()) {
			try {
				BigDecimal val = new BigDecimal(sorter.progress()).setScale(2, RoundingMode.HALF_EVEN);
				System.out.println("Progress percentage:     " + val.toPlainString());
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken - " + (endTime - startTime) + " in milli");

	}

	public static int[] copyArray(int[] a) {
		int[] copy = new int[a.length];
		System.arraycopy(a, 0, copy, 0, a.length);
		return copy;
	}

	public static int[] makeRandomArray(int n) {
		int[] a = new int[n];
		Random random = new Random();
		for (int i = 0; i < a.length; i++)
			// use small #'s for small n for "pretty printing"
			a[i] = ((n < 100) ? random.nextInt(100) : random.nextInt());
		return a;
	}

}
