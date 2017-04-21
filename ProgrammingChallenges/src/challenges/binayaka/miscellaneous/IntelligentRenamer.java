package challenges.binayaka.miscellaneous;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IntelligentRenamer {
	// Pattern match = Pattern.compile("\\S+(\\d\\d\\d)\\S+");
	Pattern match = Pattern.compile("c\\d{2}");

	public static void main(String[] args) {
		IntelligentRenamer runner = new IntelligentRenamer();
		runner.run();
	}

	public void run() {
		String path = "C:/Users/Binayaka/Downloads/Manga/Eyeshield 21 manga [Complete] [HQ]/Actual Manga/";
		File folder = new File(path);
		System.out.println(folder.getAbsolutePath());
		if (folder.exists() && folder.isDirectory()) {
			File[] folders = folder.listFiles();
			for (File file : folders) {
				if (file.exists() && !file.isDirectory()) {
					String name = file.getName();
					Path source = Paths.get(file.getAbsolutePath());
					String newName = getNewName(name);
					if (newName != null) {
						// System.out.println(newName);
						try {
							Files.move(source, source.resolveSibling(newName));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		System.out.println("Completed");
	}

	private String getNewName(String name) {
		Matcher matcher = match.matcher(name);
		while (matcher.find()) {
			String found = matcher.group();
			return "Eyeshield_21_[0" + found.substring(1) + "].cbz";
		}
		return null;
	}
}
