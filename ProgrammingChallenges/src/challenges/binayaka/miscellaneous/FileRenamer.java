package challenges.binayaka.miscellaneous;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;

public class FileRenamer {

	class FileSorter implements Comparator<File> {

		@Override
		public int compare(File o1, File o2) {
			return o1.getAbsolutePath().compareTo(o2.getAbsolutePath());
		}

	}

	public static void main(String[] args) {
		FileRenamer ren = new FileRenamer();
		ren.run();
	}

	public void run() {
		String path = "C:/Users/Binayaka/Downloads/Manga/Eyeshield 21 manga [Complete] [HQ]/Actual Manga/";
		File folder = new File(path);
		System.out.println(folder.getAbsolutePath());
		if (folder.exists() && folder.isDirectory()) {
			File[] folders = folder.listFiles();
			Arrays.sort(folders, new FileSorter());
			for (File f : folders) {
				if (f.exists() && f.isDirectory()) {
					File[] innerFiles = f.listFiles();
					// move the items from this directory to the parent
					// directory
					for (File inner : innerFiles) {
						Path source = Paths.get(inner.getAbsolutePath());
						Path newSource = Paths.get(source.getParent().getParent().toAbsolutePath().toString() + "\\"
								+ source.getFileName().toString());
						try {
							Files.move(source, newSource);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
}
