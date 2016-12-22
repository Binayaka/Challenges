package challenges.binayaka.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;

/**
 * This became necessary as JAVA can't cast primitives to generics. Sigh <br/>
 * See <a href=
 * "http://stackoverflow.com/questions/2721546/why-dont-java-generics-support-primitive-types">http://stackoverflow.com/questions/2721546/why-dont-java-generics-support-primitive-types</a>
 * for more info
 * 
 * @author Binayaka
 *
 */
public class IterativeIntegerMergeSort {

	private boolean completed;
	private Comparator<Integer> comparator;
	private int[] objectsToBeSorted;
	private int blockSize;
	private int start;
	private float prevProg;

	/**
	 * Our instantiation, because we will be querying this
	 * 
	 * @param objects
	 *            the object to sort
	 * @param compare
	 *            the comparator.
	 */
	public IterativeIntegerMergeSort(int[] objects, Comparator<Integer> compare) {
		objectsToBeSorted = objects;
		completed = false;
		comparator = compare;
	}

	public IterativeIntegerMergeSort(int[] objects) {
		objectsToBeSorted = objects;
		completed = false;
		comparator = null;
	}

	public IterativeIntegerMergeSort() {
		objectsToBeSorted = null;
		completed = false;
		comparator = null;
	}

	/**
	 * To get around the type inference issues
	 * 
	 * @param arr
	 */
	public void initializeParameters(int[] arr) {
		objectsToBeSorted = arr;
	}

	public void initializeCompator(Comparator<Integer> comp) {
		comparator = comp;
	}

	/**
	 * This will start the sorting.
	 */
	public void executeSort() {
		System.out.println("beginning sort");
		Thread sortThread = new Thread(new Runnable() {

			@Override
			public void run() {
				iterativeMergesort(objectsToBeSorted);
			}
		});
		sortThread.setName("Iterative Merge Sorter");
		sortThread.start();
	}

	/**
	 * Has our comparison finished?
	 * 
	 * @return
	 */
	public boolean isCompleted() {
		return completed;
	}

	public int[] queryContents() {
		synchronized (this) {
			int[] newList = new int[objectsToBeSorted.length];
			System.arraycopy(objectsToBeSorted, 0, newList, 0, objectsToBeSorted.length);
			return newList;

		}
	}

	/**
	 * This will return an approximation of the progress done
	 * 
	 * @return
	 */
	public float progress() {
		float majorProgress = Utils.map(blockSize, 0, objectsToBeSorted.length, 0, 100);
		BigDecimal prevVal = new BigDecimal(prevProg).setScale(2, RoundingMode.HALF_EVEN);
		BigDecimal newVal = new BigDecimal(majorProgress).setScale(2, RoundingMode.HALF_EVEN);
		if (prevVal.compareTo(newVal) >= 0) {
			float minorProgress = Utils.map(start, 0, objectsToBeSorted.length, 0, 100);
			majorProgress = (float) (prevProg + (0.01 * minorProgress));
		}
		prevProg = majorProgress;
		return majorProgress;
	}

	public void iterativeMergesort(int[] a) {
		int[] aux = new int[a.length];
		for (blockSize = 1; blockSize < a.length; blockSize *= 2) {
			for (start = 0; start < a.length; start += 2 * blockSize) {
				merge(a, aux, start, start + blockSize, start + 2 * blockSize);
			}
		}
		completed = true;
	}

	public void iterativeMergesortWithoutCopy(int[] a) {
		int[] from = a, to = new int[a.length];
		for (int blockSize = 1; blockSize < a.length; blockSize *= 2) {
			for (int start = 0; start < a.length; start += 2 * blockSize) {
				mergeWithoutCopy(from, to, start, start + blockSize, start + 2 * blockSize);
			}
			int[] temp = from;
			from = to;
			to = temp;
		}
		if (a != from) {
			// copy back
			for (int k = 0; k < a.length; k++) {
				a[k] = from[k];
			}
		}
		completed = true;
	}

	private void mergeWithoutCopy(int[] from, int[] to, int lo, int mid, int hi) {
		// DK: cannot just return if mid >= a.length, but must still copy
		// remaining elements!
		// DK: add two tests to first verify "mid" and "hi" are in range
		if (mid > from.length)
			mid = from.length;
		if (hi > from.length)
			hi = from.length;
		int i = lo, j = mid;
		for (int k = lo; k < hi; k++) {
			if (i == mid)
				to[k] = from[j++];
			else if (j == hi)
				to[k] = from[i++];
			else if (compare(from[j], from[i]) < 0)
				to[k] = from[j++];
			else
				to[k] = from[i++];
		}
		// DO NOT copy back
		// for (int k = lo; k < hi; k++)
		// a[k] = aux[k];
	}

	/**
	 * This will compare our integers
	 * 
	 * @param thisOne
	 * @param thatOne
	 * @return
	 */
	private int compare(Integer thisOne, Integer thatOne) {
		if (comparator == null) {
			return thisOne.compareTo(thatOne);
		} else {
			return comparator.compare(thisOne, thatOne);
		}

	}

	/////////////////////////////////////////
	// Recursive mergeSort, adapted from:
	// Sedgewick and Wayne, Introduction to Programming in Java
	// http://www.cs.princeton.edu/introcs/42sort/MergeSort.java.html
	/////////////////////////////////////////

	private void merge(int[] a, int[] aux, int lo, int mid, int hi) {
		// DK: add two tests to first verify "mid" and "hi" are in range
		if (mid >= a.length)
			return;
		if (hi > a.length)
			hi = a.length;
		int i = lo, j = mid;
		for (int k = lo; k < hi; k++) {
			if (i == mid)
				aux[k] = a[j++];
			else if (j == hi)
				aux[k] = a[i++];
			else if (compare(a[j], a[i]) < 0)
				aux[k] = a[j++];
			else
				aux[k] = a[i++];
		}
		// copy back
		for (int k = lo; k < hi; k++)
			a[k] = aux[k];
	}

	public void recursiveMergesort(int[] a, int[] aux, int lo, int hi) {
		// base case
		if (hi - lo <= 1)
			return;
		// sort each half, recursively
		int mid = lo + (hi - lo) / 2;
		recursiveMergesort(a, aux, lo, mid);
		recursiveMergesort(a, aux, mid, hi);
		// merge back together
		merge(a, aux, lo, mid, hi);
	}

	public void recursiveMergesort(int[] a) {
		int n = a.length;
		int[] aux = new int[n];
		recursiveMergesort(a, aux, 0, n);
		completed = true;
	}

}
