package br.com.elementi.core.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.elementi.core.exception.NotAllowException;

public class Regex {

	private static Matcher compile(String regex, String value) {
		return Pattern.compile(regex).matcher(value);
	}

	public static Matcher beanAction(String beanAction) {
		return compile("#\\{[A-Za-z]*\\.[A-Za-z]*\\}", beanAction);
	}

	public static Matcher plaque(String plaque) {
		return compile("[A-Z]{3}[0-9]{4}", plaque);
	}

	public static Matcher pack(String pack) {
		return compile("\\.[a-z]*$", pack);
	}

	public static Matcher project(String project) {
		return compile("titan-[a-z]*", project);
	}

	public static Matcher ear(String value) {
		return compile("\\.ear/.*", value);
	}

	public static Matcher jar(String value) {
		return compile(".*\\.ear/", value);
	}

	public static boolean classFile(String value) {
		return compile("[0-9a-z]{2}\\.class$", value).find();
	}

	public static boolean properties(String value) {
		return compile("\\.properties$", value).find();
	}

	public static String formatInClassLoaderPath(String value) {
		return compile("\\.(?!class$|properties$|xml$)", value).replaceAll("/");
	}

	public static boolean sql(String value) {
		return compile("\\.sql$", value).find();
	}

	public static boolean sqlCreate(String value) {
		return compile("[c C]reate\\.sql$", value).find();
	}

	public static boolean sqlDrop(String value) {
		return compile("[d D]rop\\.sql$", value).find();
	}

	public static String replaceDotForUnderscor(String value) {
		return compile("\\.", value).replaceAll("_");
	}

	public static String extractJarFile(String value) throws Exception {
		Matcher compiled = compile("[\\p{Alnum}-\\.]*\\.jar", value);
		if (compiled.find()) {
			return compiled.group();
		}
		throw new NotAllowException(value);
	}

	public static String changeTablesCreateForDrop(String value) throws Exception {
		StringBuilder builder = new StringBuilder();
		Matcher matcher = compile("create (table|sequence) [a-z_ ]*(\\(| process)", value.toLowerCase());
		while (matcher.find()) {
			builder.append(matcher.group().replaceAll("(\\(| start)", ";"));
		}
		return builder.toString().replaceAll("create", "drop");
	}

	public static String changeCreateConstraintForDrop(String value) {
		StringBuilder drop = new StringBuilder("DROP ");
		Matcher compile = compile("constraint [a-zA-Z_]*", value);
		if (compile.find()) {
			return drop.append(compile.group()).toString();
		}
		return value;
	}

	public static String capitalize(String value) {
		return value.substring(0,1).toUpperCase() + value.substring(1);
	}

}
