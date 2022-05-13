package br.com.elementi.core.model;

import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import br.com.elementi.core.tools.Extract;

@Deprecated
public class PathZip {

	private String zipPath;
	private String jarName;

	private PathZip(String zipPath, String jarName) {
		this.zipPath = zipPath;
		this.jarName = jarName;
	}

	public static PathZip extractPathZip(String zipPath, String jarName) {
		return new PathZip(zipPath, jarName);
	}

	public static PathZip extractPathZip(String path) {
		String earPath = Extract.earPath(path);
		String jarName = Extract.jarName(path);
		return extractPathZip(earPath, jarName);
	}

	public String zipPath() {
		return zipPath;
	}

	public ZipFile zipFile() throws Exception {
		try {
			return new ZipFile(zipPath);
		} catch (Exception e) {
			throw new IllegalArgumentException("PathZip zip erro :: " + e.getMessage() + " :: " + zipPath);
		}
	}

	public String jarName() {
		return jarName;
	}

	public ZipEntry zipEntry() throws Exception {
		ZipEntry entry = zipFile().getEntry(jarName);
		if (entry == null) {
			throw new IllegalArgumentException("Entry not found :: " + jarName);
		}
		return entry;
	}

	public InputStream zipEntryInputStream() throws Exception {
		return zipFile().getInputStream(zipEntry());
	}
}
