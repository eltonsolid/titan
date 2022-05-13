package br.com.elementi.core.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import br.com.elementi.core.domain.DomainException;

@Target(TYPE)
@Retention(RUNTIME)
public @interface QTableNotFoundException {

	Class<? extends DomainException> value();

}
