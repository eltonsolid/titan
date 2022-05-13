package br.com.elementi.core.resource;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import br.com.elementi.core.tools.Regex;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

public class Resources {

	private final String valueUsedInClassLoader;
	private final List<ResourceStream> itens;

	public class ClassLoaderItem {
		public ClassLoaderItem(String protocol, String path) {
			this.protocol = protocol;
			this.path = path;
		}

		protected String protocol;
		protected String path;
	}

	private Resources(String valueUsedInClassLoader, List<ResourceStream> itens) {
		this.valueUsedInClassLoader = valueUsedInClassLoader;
		this.itens = itens;
	}

	private Resources(String valueUsedInClassLoader) {
		this.valueUsedInClassLoader = valueUsedInClassLoader;
		this.itens = Lists.newArrayList();
	}

	public static Resources allWithContext(String context) throws Exception {
		return new Resources(context).searchContext();
	}

	private Resources searchContext() throws Exception {
		List<ClassLoaderItem> itens = Lists.newArrayList();
		List<ClassLoaderItem> loaderItems = classLoader();
		for (ClassLoaderItem item : loaderItems) {
			itens.add(new ClassLoaderItem(item.protocol, item.path.replaceAll(valueUsedInClassLoader, "")));
		}
		return loader(itens);
	}

	public static Resources searchWith(String path) throws Exception {
		return new Resources(Regex.formatInClassLoaderPath(path)).search();
	}

	private Resources search() throws Exception {
		List<ClassLoaderItem> classLoader = classLoader();
		return loader(classLoader);
	}

	private Resources loader(List<ClassLoaderItem> loaderItems) throws Exception {
		for (ClassLoaderItem item : loaderItems) {
			switch (item.protocol) {
			case "file":
				fromFile(new File(item.path));
				break;
			case "jar":
				fromZip(new ZipFile(item.path.split("!")[0].replaceAll("file:", "")));
				break;
			}
		}
		return this;
	}

	public void fromZip(ZipFile zipFile) throws Exception {
		Enumeration<? extends ZipEntry> entries = zipFile.entries();
		while (entries.hasMoreElements()) {
			ZipEntry entry = entries.nextElement();
			if (!entry.isDirectory()) {
				itens.add(new ResourceStream(entry.getName(), zipFile.getInputStream(entry)));
			}
		}
	}

	private void fromFile(File file) throws Exception {
		if (file.isFile()) {
			itens.add(new ResourceStream(file.getName(), new FileInputStream(file)));
		} else {
			for (File fileIn : file.listFiles()) {
				fromFile(fileIn);
			}
		}

	}

	private List<ClassLoaderItem> classLoader() throws Exception {
		List<ClassLoaderItem> itens = Lists.newArrayList();
		Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(valueUsedInClassLoader);
		while (resources.hasMoreElements()) {
			URL next = resources.nextElement();
			itens.add(new ClassLoaderItem(next.getProtocol(), next.getPath()));
		}
		return itens;
	}

	public Integer size() {
		return itens.size();
	}

	public List<ResourceStream> filterProperties() {
		return resourceFilter(ResourceStream.PROPERTIES);
	}

	public List<ResourceStream> filterSql() {
		return resourceFilter(ResourceStream.SQL);
	}

	public List<ResourceStream> filterClassFiles() {
		return resourceFilter(ResourceStream.CLASS_FILE);
	}

	public List<ResourceStream> filter(Predicate<ResourceStream> predicate) {
		return resourceFilter(predicate);
	}

	private List<ResourceStream> resourceFilter(Predicate<ResourceStream> predicate) {
		List<ResourceStream> resourceFiles = Lists.newArrayList();
		for (ResourceStream resource : itens) {
			if (predicate.apply(resource)) {
				resourceFiles.add(resource);
			}
		}
		return resourceFiles;
	}

	@Override
	public String toString() {
		return "ValueUsedInClassLoader >> " + valueUsedInClassLoader + " :: " + "SIZE >> " + size();
	}
}
