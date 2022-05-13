package br.com.elementi.core.constraint;

import java.util.Collection;
import java.util.List;

import br.com.elementi.core.exception.NotAllowException;
import br.com.elementi.core.exception.NotEmptyException;
import br.com.elementi.core.exception.NotNullException;
import br.com.elementi.core.tools.Reflect;
import br.com.elementi.core.tools.ReflectValue;
import br.com.elementi.core.tools.TraceException;

public class Constraints {

	// TODO O processo de constraints gerou uma necessidade, ou mesmo um
	// lembrete de que se faz necessario a criação de
	// um resolver para o processso de exception

	public static <T> T nothingNull(T object) throws Exception {
		List<ReflectValue> fields = Reflect.allFieldValuesOnBean(object);
		for (ReflectValue field : fields) {
			if (field.isEmpty()) {
				TraceException.throwable(NotNullException.class, field.getField().getName());
			}
		}
		return object;
	}

	public static <T> T notNull(T object) throws Exception {
		if (object == null) {
			TraceException.throwable(NotNullException.class);
		}
		return object;
	}

	public static String notEmpty(String value) throws Exception {
		if (notNull(value).isEmpty()) {
			TraceException.throwable(NotEmptyException.class);
		}
		return value;
	}

	public static <T extends Collection<?>> T notEmpty(T collection) throws Exception {
		if (notNull(collection).isEmpty()) {
			TraceException.throwable(NotEmptyException.class);
		}
		return collection;
	}

	public static void notAllow(Boolean argument) throws Exception {
		if (argument) {
			TraceException.throwable(NotAllowException.class);
		}
	}

}
