package com.findfromjar;

/*
 * This class having a method to find the file from jar and read that file and store into temporary property file
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.json.JSONException;

public class FindJarEntry {
	static File value;
	static String fullpath2;
	static File fpath;

	private static File process(InputStream input) throws IOException {

		InputStreamReader isr = new InputStreamReader(input);
		BufferedReader reader = new BufferedReader(isr);
		String line;
		Path path1 = Paths.get("C://JSONResult//convertedjson");
		if (!Files.exists(path1)) {
			Files.createDirectories(path1);
		}

		File f = new File("C://JSONResult//convertedjson/dummyProp.properties");

		if (!f.exists()) {
			f.createNewFile();
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(f.getAbsoluteFile(), false));

		while ((line = reader.readLine()) != null) {
			bw.write("\n" + line);
		}
		bw.close();
		reader.close();

		fpath = f.getAbsoluteFile();

		return fpath;

	}

	@SuppressWarnings("resource")
	public static File main1(String fullpath, String path, String file) throws IOException, JSONException {
		String myClass = "";
		JarFile jarFile;
		if (path.contains(".war")) {

			myClass = path.substring(0, path.lastIndexOf('.') + 4);
			jarFile = new JarFile(myClass);

		} else {
			jarFile = new JarFile(path);

		}

		final Enumeration<JarEntry> entries = jarFile.entries();
		while (entries.hasMoreElements()) {
			final JarEntry entry = entries.nextElement();

			if (entry.getName().contains(".")) {
				String name = entry.getName().substring(entry.getName().lastIndexOf("/") + 1);
				if (name.equals(file)) {

					JarEntry fileEntry = jarFile.getJarEntry(entry.getName());
					InputStream input = jarFile.getInputStream(fileEntry);
					value = process(input);
					break;
				}

			}

		}

		return value;
	}

}
