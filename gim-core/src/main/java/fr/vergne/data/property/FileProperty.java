package fr.vergne.data.property;

import java.io.File;

public class FileProperty extends Property<File> {

	public FileProperty(File file) {
		set(file);
	}
	
	public FileProperty(String path) {
		set(new File(path));
	}
}
