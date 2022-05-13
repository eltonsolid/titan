package br.com.elementi.core.tools;

import java.util.regex.Matcher;

public class Extract {

	private static final String EAR = ".ear";

	public static String earPath(String value) {
		return Regex.ear(value).replaceAll("") + EAR;
	}

	public static String jarName(String value) {
		return Regex.jar(value).replaceAll("");
	}

	public static String projectName(String value) {
		Matcher project = Regex.project(value);
		return project.find() ? project.group() : "";
	}

}
