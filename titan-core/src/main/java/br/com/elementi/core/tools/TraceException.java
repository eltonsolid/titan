package br.com.elementi.core.tools;

import br.com.elementi.core.domain.DomainException;

public class TraceException {

	public static void throwable(Class<? extends DomainException> exeception) throws Exception {
		throwable(exeception, "");
	}

	public static void throwable(Class<? extends DomainException> exeception, String parameter) throws Exception {
		StackTraceElement stack = Thread.currentThread().getStackTrace()[4];
		String trace = stack.getClassName() + "." + stack.getMethodName();
		String lineNumber = String.valueOf(stack.getLineNumber());
		Exception instance = (Exception) Reflect.instance(exeception,
				trace + "( " + parameter + " ) >> [ " + lineNumber + " ]");
		throw instance;
	}
}
