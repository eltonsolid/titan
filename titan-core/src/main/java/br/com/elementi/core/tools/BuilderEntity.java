package br.com.elementi.core.tools;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import com.google.common.collect.Lists;

import br.com.elementi.core.domain.DomainEntity;
import br.com.elementi.core.exception.NotExpectedException;
import br.com.elementi.core.exception.NotFoundException;
import br.com.elementi.core.model.Identity;
import br.com.elementi.core.qtable.QTable;
import javassist.util.proxy.MethodHandler;

public class BuilderEntity {

	public interface BuilderTemplate<E extends DomainEntity> {

		public <T extends BuilderTemplate<?>> T id(Identity id);

		public E get();

		public List<E> list();

	}

	public static <T extends BuilderTemplate<? extends DomainEntity>> T entity(Class<T> builder) throws Exception {
		MethodHandler handler = handlerEntity(Init.initializer(Reflect.getClassDeclarated(builder)), builder);
		return Reflect.proxyInstance(builder, handler);
	}

	public static <T extends BuilderTemplate<? extends DomainEntity>> T table(QTable table, Class<T> builder) throws Exception {
		MethodHandler handler = handleTable(table, builder);
		return Reflect.proxyInstance(builder, handler);
	}

	private static MethodHandler handleTable(QTable table, Class<?> builder) {
		return new MethodHandler() {
			@Override
			public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
				switch (thisMethod.getName()) {
				case "get":
					return table.find();
				case "list":
					return table.list();
				default:
					addConstraint(table, thisMethod.getName(), args[0]);
					return self;
				}
			}

			private void addConstraint(QTable table, String fieldName, Object value) {
				table.eq(fieldName, value);
			}
		};
	}

	private static MethodHandler handlerEntity(DomainEntity entity, Class<?> builder) {
		return new MethodHandler() {
			public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
				switch (thisMethod.getName()) {
				case "get":
					return entity;
				case "list":
					return Lists.newArrayList(entity);
				default:
					validateTypeReturn(builder, thisMethod.getReturnType());
					setField(entity, thisMethod, args);
					return self;
				}
			}

			private void validateTypeReturn(Class<?> builder, Class<?> returnType) throws Exception {
				if (!returnType.isAssignableFrom(builder)) {
					throw new NotExpectedException("Expected type :: " + builder.getSimpleName() + " but found ::" + returnType.getSimpleName());
				}
			}

			private void setField(DomainEntity entity, Method thisMethod, Object[] args) throws Exception {
				Field field = field(entity, thisMethod);
				field.setAccessible(true);
				field.set(entity, args[0]);
			}

			private Field field(DomainEntity entity, Method thisMethod) throws Exception {
				try {
					return entity.getClass().getDeclaredField(thisMethod.getName());
				} catch (Exception exception) {
					throw new NotFoundException("Not found Field with name :: " + thisMethod.getName());
				}
			}

		};
	}
}
