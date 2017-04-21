package challenges.binayaka.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StdinReader {
	public static void main(String[] args) {
		StdinReader reader = new StdinReader();
		reader.run();
	}

	private void run() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new InputStreamReader(System.in));
			String line = reader.readLine();
			while(line!=null){
				
			}
			if(reader!=null){
				reader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
