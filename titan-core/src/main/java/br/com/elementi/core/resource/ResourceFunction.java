package br.com.elementi.core.resource;

import java.net.URL;

@Deprecated
public interface ResourceFunction {

	public static final ResourceFunction CONTEXT = new ResourceFunction() {
		public String apply(URL url, String context) throws Exception {
			return url.getPath().replace(context, "");
		}

		public String toString() {
			return "CONTEXT";
		};
	};

	public static final ResourceFunction FILE = new ResourceFunction() {
		public String apply(URL url, String context) throws Exception {
			return url.getPath();
		}

		public String toString() {
			return "FILE";
		}
	};
	public static final ResourceFunction DIR = new ResourceFunction() {
		public String apply(URL url, String context) throws Exception {
			return url.getPath();
		}

		public String toString() {
			return "DIR";
		}
	};

	public String apply(URL url, String context) throws Exception;

}
