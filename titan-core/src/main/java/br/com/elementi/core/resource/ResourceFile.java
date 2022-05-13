package br.com.elementi.core.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Set;
import java.util.zip.ZipFile;

import br.com.elementi.core.tools.Regex;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

@Deprecated
public class ResourceFile implements Iterable<InputStream> {

	private Set<InputStream> itens;

	private ResourceFile() {
		itens = Sets.newHashSet();
	}

	private ResourceFile(Set<InputStream> itens) {
		this.itens = itens;
	}

	public static ResourceFile create(String path) throws Exception {
		return create(new File(path));
	}

	public static ResourceFile create(File file) throws Exception {
		return new ResourceFile().build(file);
	}

	public static void create(ZipFile zipFile) {
	}

	private ResourceFile build(File file) throws Exception {
		add(file);
		return this;
	}

	private void add(File file) throws Exception {
		if (file.isFile()) {
			itens.add(new FileInputStream(file));
		} else {
			add(Sets.newHashSet(file.listFiles()));
		}
	}

	private void add(Set<File> files) throws Exception {
		for (File file : files) {
			add(file);
		}
	}

	public Integer size() {
		return itens.size();
	}

	public Iterator<InputStream> iterator() {
		return itens.iterator();
	}

	public ImmutableSet<InputStream> itens() {
		return ImmutableSet.copyOf(itens);
	}

	public ResourceFile filter(Predicate<InputStream> predicate) {
		return new ResourceFile(Sets.filter(itens, predicate));
	}

	@Override
	public String toString() {
		return "SIZE :: " + size() + "  ITENS :: " + itens;

	}

}
