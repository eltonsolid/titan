package br.com.elementi.core.tools;

import java.lang.reflect.Method;

import javassist.util.proxy.MethodHandler;
import br.com.elementi.core.domain.DomainException;
import br.com.elementi.core.exception.NotExpectedException;

public class Watcher {
	//TODO esta classe esta se inclinando para se tornar uma classe de teste para o auxilio nos testes

	//TODO Criar um forma de escolher fazer um print da exeception, sem para lançar uma outra, foi uma ideia bem superficial. preciso pensar melhor na ideia.
	//TODO Quando eu recebo um execption, quase sempre eu preciso fazer alguma coisa antes da exption subir, executar um metodo.Por exemplo eu preciso procurar um cliente
	// mas seu nao achar eu preciso limpar alguns campos na tela, para melhorar a experiencia do usuario.Esta nao é a primeira vez que surge uma necessidade destas.
	// Odesenvolvimento direcionado a execption cria uma grande dependencia no wacther, o qual seja muito interessante de pesquisar.
	@SuppressWarnings("unchecked")
	public static <T> T that(T instance, Class<? extends Exception> exception) throws Exception {
		MethodHandler handler = handler(instance, exception);
		T proxy = (T) Reflect.proxyInstance(instance.getClass(), handler);
		return proxy;
	}

	private static <T> MethodHandler handler(final T t, final Class<? extends Exception> handlerClass) {
		return new MethodHandler() {
			public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
				try {
					System.out.println("Watcher.handler(...).new MethodHandler() {...}.invoke()");
					return thisMethod.invoke(t, args);
				} catch (Exception exception) {
					if (exception.getCause() instanceof DomainException) {
						throw domainException(handlerClass, exception);
					}
					throw new NotExpectedException((Exception) exception.getCause(), "Method :: "
							+ thisMethod.getName());
				}
			}

			private Exception domainException(final Class<? extends Exception> handlerClass, Exception exception)
					throws Exception {
				return (Exception) Reflect.instance(handlerClass, new Class<?>[] { DomainException.class },
						exception.getCause());
			}
		};
	}
}
