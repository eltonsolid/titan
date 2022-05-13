package br.com.elementi.core.junit;

import java.util.List;

import javassist.ClassPool;
import javassist.CtMethod;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import br.com.elementi.core.Integration;

import com.google.common.collect.Lists;

public class UnitRunner extends BlockJUnit4ClassRunner {
	//TODO Criar uma forma de obter a menssagem da exception, a validação do retorno sera util quando
	// Uma exception de validação geral como notallow for gerada, onde o valor passado como info pode ser validada
	// O JUnit tem um objeto exeptedexception, mas nao funciona de forma corrrta por causa desta implementação que
	// prove uma forma de teste direcionada para o BDD
	

	private Object instance;

	public UnitRunner(Class<?> klass) throws InitializationError {
		super(klass);
		try {
			instance = getTestClass().getOnlyConstructor().newInstance();
		} catch (Exception e) {
			throw new InitializationError(e);
		}
	}

	@Override
	protected Object createTest() throws Exception {
		return instance;
	}

	@Override
	protected Statement withBeforeClasses(Statement statement) {
		final Statement withBeforeClasses = super.withBeforeClasses(statement);
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				if (instance instanceof Integration<?>) {
					Integration<?> integration = (Integration<?>) instance;
					try {
						integration.beforeIntegration();
					} catch (Exception exception) {
						exception.printStackTrace();
						throw exception;
					}
				}
				withBeforeClasses.evaluate();
			}
		};
	}

	@Override
	protected Statement withAfterClasses(Statement statement) {
		final Statement withAfterClasses = super.withAfterClasses(statement);
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				withAfterClasses.evaluate();
				if (instance instanceof Integration<?>) {
					Integration<?> integration = (Integration<?>) instance;
					integration.afterIntegration();
				}
			}
		};
	}

	@Override
	protected List<FrameworkMethod> getChildren() {
		List<FrameworkMethod> order = Lists.newArrayList();
		CtMethod[] methods = methodsInOrder();
		for (CtMethod method : methods) {
			for (FrameworkMethod child : super.getChildren()) {
				if (child.getName().endsWith(method.getName())) {
					order.add(child);
				}
			}
		}
		return order;
		/*Collections.sort(children, new Comparator<FrameworkMethod>() {
			public int compare(FrameworkMethod fromMethod, FrameworkMethod toMethod) {
				Order testFrom = fromMethod.getAnnotation(Order.class);
				Order testTo = toMethod.getAnnotation(Order.class);
				Integer valueFrom = (testFrom == null) ? Integer.MAX_VALUE - children.size(): testFrom.value();
				Integer valueTo = (testTo == null) ? Integer.MAX_VALUE - children.size() : testTo.value();
				return valueFrom.compareTo(valueTo);
			}

		});
		return children;*/
	}

	private CtMethod[] methodsInOrder(){
		try {
			return ClassPool.getDefault().get(instance.getClass().getName()).getDeclaredMethods();
		} catch (Exception e) {
			return new CtMethod[]{};
		}
	}
}
