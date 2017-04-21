package challenges.binayaka.common;

public class StairCase {
	public static void main(String[] args) {
		stairCaseSoln(6);
	}

	static void stairCaseSoln(int n) {
		for (int i = 0; i < n; i++) {
			StringBuilder builder = new StringBuilder();
			for (int j = n - i - 1; j > 0; j--) {
				builder.append(" ");
			}
			for(int k=0; k <= i; k++){
				builder.append("#");	
			}
			System.out.println(builder.toString());
		}
	}
}
