package br.com.elementi.core.tools;

import static br.com.elementi.core.constraint.Constraints.notAllow;
import static br.com.elementi.core.constraint.Constraints.notNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.joda.time.DateTime;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import br.com.elementi.core.domain.DomainEnum;
import br.com.elementi.core.exception.NotFoundException;
import br.com.elementi.core.exception.NullConstructionException;
import br.com.elementi.core.mapper.MapperField;
import br.com.elementi.core.mapper.MapperNode;
import br.com.elementi.core.model.Identity;
import br.com.elementi.core.model.ReflectDefaultValue;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;
import sun.reflect.ReflectionFactory;

public class Reflect {

	private static final Constructor<?> DEFAULT_CONSTRUCTOR = Object.class.getDeclaredConstructors()[0];

	public static final ReflectDefaultValue DEFAULT = new ReflectDefaultValue() {
		public Object reflectDefaultValue(Class<?> type) throws Exception {
			return instance(type);
		}
	};

	static class ReflectClass {
		public Class<?> classe;
		public Class<?> invoke;

		private ReflectClass(Class<?> classe, Class<?> invoke) {
			super();
			this.classe = classe;
			this.invoke = invoke;
		}

		public ReflectClass(Class<?> classe) {
			this.classe = classe;
			this.invoke = classe;
		}

		public boolean hasNotSuperClass() {
			return Object.class.equals(invoke.getSuperclass());
		}

		public ReflectClass up() {

			return new ReflectClass(classe, invoke.getSuperclass());
		}

	}

	public static Object instance(Class<?> classe) throws Exception {
		return instance(classe, new Class<?>[] {}, new Object[] {});
	}

	public static Object instance(Class<?> classe, Object... args) throws Exception {
		Class<?>[] classes = convertInClasses(args);
		return instance(classe, classes, args);
	}

	public static Object instance(Class<?> classe, Class<?>[] classes, Object... args) throws Exception {
		try {
			Constructor<?> constructor = classe.getDeclaredConstructor(classes);
			constructor.setAccessible(true);
			// constructorAccessor
			return constructor.newInstance(args);
		} catch (Exception e) {
			throw new NullConstructionException("Classe has not a default construction ::: " + classe.getSimpleName());
		}
	}

	public static <T> T forceInstance(Class<T> t) throws Exception {
		ReflectionFactory reflectionFactory = ReflectionFactory.getReflectionFactory();
		Constructor<T> constructor = (Constructor<T>) reflectionFactory.newConstructorForSerialization(t, DEFAULT_CONSTRUCTOR);
		return (T) constructor.newInstance(new Object[] {});
	}

	public static <T> Class<T> getClassDeclaratedInside(Class<?> clazz) {
		Class<T> classDeclarated = getClassDeclarated(clazz, 0);
		return getClassDeclarated(classDeclarated, 0);
	}

	public static <T> Class<T> getClassDeclarated(Class<?> clazz) {
		return getClassDeclarated(clazz, 0);
	}

	@SuppressWarnings("unchecked")
	public static <T> Class<T> getClassDeclarated(Class<?> clazz, int index) {
		try {
			ParameterizedType genericSuperclass = (ParameterizedType) (clazz.isInterface() ? clazz.getGenericInterfaces()[0] : clazz.getGenericSuperclass());
			Type type = genericSuperclass.getActualTypeArguments()[index];
			return (Class<T>) type;
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}

	public static boolean hasField(Class<?> classe, String fieldName) {
		try {
			Class<?> found = classe;
			for (String field : fieldName.split("\\.")) {
				found = found.getDeclaredField(field).getType();
			}
			return true;
		} catch (Exception e) {
			System.out.println("FIELD NOT FOUND :: " + fieldName);
			return false;
		}
	}

	public static List<ReflectValue> allFieldValuesOnBean(Object from) throws Exception {
		List<ReflectValue> fields = Lists.newArrayList();
		for (Field field : from.getClass().getDeclaredFields()) {
			if (isNotStatic(field)) {
				fields.add(new ReflectValue(field, getValueFrom(from, field.getName())));
			}
		}
		return fields;
	}

	// Change the method name to mapper, and return um mapper object, equal to
	// object in resolver method
	public static ReflectPath findBound(Class<?> from, Class<?> to) throws Exception {
		for (Field fromField : from.getDeclaredFields()) {
			for (Field toField : to.getDeclaredFields()) {
				String found = findBound(fromField, toField);
				if (found != null) {
					return new ReflectPath(fromField.getName(), found);
				}
			}
		}
		throw new NotFoundException(from.getSimpleName() + " ::: Not contain bound with class ::: " + to.getSimpleName());
	}

	private static String findBound(Field fromField, Field toField) {
		if (isJavaClass(fromField.getType())) {
			return null;
		}
		if (fromField.getType().equals(toField.getType())) {
			return toField.getName();
		}
		if (fromField.getDeclaringClass().equals(toField.getType())) {
			return toField.getName() + "." + fromField.getName();
		}
		return null;
	}

	private static boolean isJavaClass(Class<?> type) {
		boolean isJavaClass = isDouble(type) || isInteger(type) || isLong(type) || isString(type) || isEnum(type) || isClass(type) || isList(type)
				|| isMap(type) || isSet(type);
		return isJavaClass;
	}

	public static class ReflectPath {
		private String fromName;
		private String toName;

		public ReflectPath(String fromName, String toName) {
			this.fromName = fromName;
			this.toName = toName;
		}

		public String fromName() {
			return fromName;
		}

		public String toName() {
			return toName;
		}

	}

	@SuppressWarnings("unchecked")
	public static <T> Class<T> proxyClass(Class<T> t) throws Exception {
		notNull(t);
		notAllow(Object.class.equals(t));
		ProxyFactory factory = new ProxyFactory();
		if (t.isInterface()) {
			factory.setInterfaces(new Class[] { t });
		} else {
			factory.setSuperclass(t);
		}
		Class<T> createClass = factory.createClass();
		return createClass;
	}

	// Renomear este metodo, depois de muito tempo, parece que podemos passar
	// apenas uma instancia, quando nao everdade;
	public static <T> T proxyInstance(Class<T> t, MethodHandler handler) throws Exception {
		Class<T> proxyClass = proxyClass(t);
		// T proxy = Reflect.forceInstance(proxyClass);
		T proxy = proxyClass.newInstance();
		((ProxyObject) proxy).setHandler(handler);
		return proxy;
	}

	public static void resolver(Object from, String fromField, Object to, String toField) throws Exception {
		resolver(from, MapperField.onGet(fromField), to, MapperField.onGet(toField));
	}

	public static void resolver(Object from, MapperField fromField, Object to, MapperField toField) throws Exception {
		Object value = getValueFrom(from, fromField);
		setValueTo(to, toField, value);
	}

	public static Object getValueFrom(Object from, String fromField) throws Exception {
		return getValueFrom(from, MapperField.onGet(fromField));
	}

	public static Object getValueFrom(Object from, MapperField fromField) throws Exception {
		for (MapperNode fieldNode : fromField) {
			from = getValue(from, fieldNode);
		}
		return from;
	}

	private static Class<?>[] convertInClasses(Object... args) {
		int count = 0;
		Class<?>[] classes = new Class<?>[args.length];
		for (Object object : args) {
			classes[count++] = object.getClass();
		}
		return classes;
	}

	private static Object getValue(Object from, MapperNode fieldItem) throws Exception {
		return isIterable(from) ? getValueFromMany((Iterable<?>) from, fieldItem) : getValueFromOne(from, fieldItem);
	}

	private static Object getValueFromMany(Iterable<?> iterable, MapperNode fieldItem) throws Exception {
		List<Object> values = Lists.newArrayList();
		for (Object from : iterable) {
			values.add(getValueFromOne(from, fieldItem));
		}
		return values;
	}

	private static Object getValueFromOne(Object from, MapperNode node) throws Exception {
		if (from == null) {
			return null;
		}
		if (node.isFieldOnly()) {
			return invokeGetField(from, node.name());
		}
		return invokeGetMethod(from, node.nameForGet());
	}

	private static Object invokeGetField(Object from, String name) throws Exception {
		Field field = field(from.getClass(), name);
		return field.get(from);
	}

	private static void invokeSetField(Object to, String name, Object value) throws Exception {
		Field field = field(to.getClass(), name);
		field.set(to, value);
	}

	private static Field field(Class<?> classe, String name) throws Exception {
		return field(new ReflectClass(classe), name);
	}

	private static Field field(ReflectClass reflectClass, String name) throws Exception {
		try {
			Field field = reflectClass.classe.getDeclaredField(name);
			field.setAccessible(true);
			return field;
		} catch (Exception exception) {
			throwExceptionOnSuperClassEqualsObject(reflectClass, name);
			return field(reflectClass.up(), name);
		}
	}

	public static void setValueTo(Object root, String toField, Object value) throws Exception {
		setValueTo(root, MapperField.onField(toField), value);
	}

	public static void setValueTo(Object root, MapperField toField, Object value) throws Exception {
		Object to = findObjectToSet(root, toField);
		value = toField.converter().converter(value);
		setValue(to, toField, value);
	}

	private static void setValue(Object to, MapperField toField, Object value) throws Exception {
		if (value != null) {
			if (toField.lastNode().isFieldOnly()) {
				invokeSetField(to, toField.lastNode().name(), value);
			} else {
				invokeSetMethod(to, toField.lastNameForSet(), value);
			}
		}
	}

	private static Object findObjectToSet(Object to, MapperField toField) throws Exception {
		int position = 0;
		while (toField.size() > position + 1) {
			to = findObjectToSet(to, toField.get(position++));
		}
		return to;
	}

	private static Object findObjectToSet(Object from, MapperNode node) throws Exception {
		Object fromGet = invokeGetMethod(from, node.nameForGet());
		if (fromGet == null) {
			fromGet = createValueToSet(from, node);
		}
		return fromGet;
	}

	private static Object createValueToSet(Object from, MapperNode fieldItem) throws Exception {
		Object defaultValue = defaultValue(from.getClass(), fieldItem.nameForGet());
		invokeSetMethod(from, fieldItem.nameForSet(), defaultValue);
		return defaultValue;
	}

	private static Object invokeGetMethod(Object from, String fromMethodName) throws Exception {
		Method method = method(from.getClass(), fromMethodName);
		return method.invoke(from);
	}

	private static void invokeSetMethod(Object to, String toMethodName, Object value) throws Exception {
		Method method = method(to.getClass(), toMethodName, defaultClass(value.getClass()));
		method.invoke(to, value);
	}

	private static Method method(Class<?> classe, String methodName, Class<?>... parameterTypes) throws Exception {
		return method(new ReflectClass(classe), methodName, parameterTypes);
	}

	private static Method method(ReflectClass reflectClass, String methodName, Class<?>... parameterTypes) throws Exception {
		try {
			return reflectClass.invoke.getDeclaredMethod(methodName, parameterTypes);
		} catch (Exception e) {
			throwExceptionOnSuperClassEqualsObject(reflectClass, methodName, parameterTypes);
			return method(reflectClass.up(), methodName, parameterTypes);
		}
	}

	private static void throwExceptionOnSuperClassEqualsObject(ReflectClass reflectClass, String name, Class<?>... parameterTypes) throws NotFoundException {
		if (reflectClass.hasNotSuperClass()) {
			String className = reflectClass.classe.getSimpleName();
			String fieldName = " ::: Not contain field/method with name ::: " + name;
			String parameters = "(" + toString(parameterTypes) + ")";
			throw new NotFoundException(className + fieldName + parameters);
		}
	}

	private static String toString(Class<?>... type) {
		if (type.length > 0) {
			return type[0].toString();
		}
		return "";
	}

	public static Class<?> defaultClass(Class<?> classe) {
		if (List.class.isAssignableFrom(classe)) {
			return List.class;
		}

		if (Map.class.isAssignableFrom(classe)) {
			return Map.class;
		}
		return classe;
	}

	private static Object defaultValue(Class<?> classe, String methodName) throws Exception {
		Method method = method(classe, methodName);
		return defaultValue(method);
	}

	private static Object defaultValue(Method method) throws Exception {
		return defaultValue(DEFAULT, method.getReturnType());
	}

	public static Object defaultValue(ReflectDefaultValue defaultValue, Class<?> type) throws Exception {
		if (isIdentity(type)) {
			return instance(type, 0);
		}
		if (isDouble(type)) {
			return 0;
		}
		if (isInteger(type)) {
			return 0;
		}
		if (isLong(type)) {
			return 0;
		}
		if (isString(type)) {
			return "";
		}
		if (isDate(type)) {
			return DateTime.now().toDate();
		}
		if (isDateTime(type)) {
			return DateTime.now();
		}
		if (isEnum(type)) {
			return enumValue(type);
		}
		if (isClass(type)) {
			return type;
		}
		if (isList(type)) {
			return Collections.EMPTY_LIST;
		}
		if (isMap(type)) {
			return Collections.EMPTY_MAP;
		}
		if (isSet(type)) {
			return Collections.EMPTY_SET;
		}

		return defaultValue.reflectDefaultValue(type);
	}

	private static boolean isSet(Class<?> type) {
		return Set.class.isAssignableFrom(type);
	}

	private static boolean isMap(Class<?> type) {
		return Map.class.isAssignableFrom(type);
	}

	private static boolean isList(Class<?> type) {
		return List.class.isAssignableFrom(type);
	}

	private static boolean isClass(Class<?> type) {
		return type.isAssignableFrom(java.lang.Class.class);
	}

	private static Object enumValue(Class<?> type) {
		Object enumerate = type.getEnumConstants()[0];
		return enumValue(enumerate);
	}

	private static Object enumValue(Object enumerate) {
		if (isDomainEnum(enumerate)) {
			return ((DomainEnum<?>) enumerate).defaultValue();
		}
		return enumerate;
	}

	private static boolean isDomainEnum(Object enumerate) {
		return DomainEnum.class.isAssignableFrom(enumerate.getClass());
	}

	private static boolean isEnum(Class<?> type) {
		return Enum.class.isAssignableFrom(type) && type.getEnumConstants().length > 0;
	}

	private static boolean isString(Class<?> type) {
		return String.class.isAssignableFrom(type);
	}

	private static boolean isDate(Class<?> type) {
		return Date.class.isAssignableFrom(type);
	}

	private static boolean isDateTime(Class<?> type) {
		return DateTime.class.isAssignableFrom(type);
	}

	private static boolean isInteger(Class<?> type) {
		return Integer.class.isAssignableFrom(type) || Integer.TYPE.isAssignableFrom(type);
	}

	private static boolean isLong(Class<?> type) {
		return Long.class.isAssignableFrom(type) || Long.TYPE.isAssignableFrom(type);
	}

	private static boolean isDouble(Class<?> type) {
		return Double.class.isAssignableFrom(type) || Double.TYPE.isAssignableFrom(type);
	}

	private static boolean isIdentity(Class<?> type) {
		return Identity.class.isAssignableFrom(type);
	}

	public static boolean isIterable(Object from) {
		return Iterable.class.isAssignableFrom(from.getClass());
	}

	@VisibleForTesting
	public static Class<?> findDestinationClass(Class<?> root, String field) throws Exception {
		return findDestinationClass(root, MapperField.onField(field));
	}

	public static Class<?> findDestinationClass(Class<?> root, MapperField field) throws Exception {
		Class<?> found = root;
		for (MapperNode node : field) {
			found = findDestinationClass(found, node);
		}
		return found;
	}

	private static Class<?> findDestinationClass(Class<?> classe, MapperNode node) throws Exception {
		if (node.isFieldOnly()) {
			return type(field(classe, node.name()));
		} else {
			return type(method(classe, node.nameForGet()));
		}
	}

	private static Class<?> type(Field field) {
		return getGenericTypeWhenExist(field.getGenericType());
	}

	private static Class<?> type(Method method) {
		return getGenericTypeWhenExist(method.getGenericReturnType());
	}

	public static Class<?> getClassForIterablesOrObject(Object from) {
		return (Class<?>) (isIterable(from) ? Iterables.getFirst((Iterable<?>) from, new Object()).getClass() : from.getClass());
	}

	private static Class<?> getGenericTypeWhenExist(Type type) {
		if (ParameterizedType.class.isAssignableFrom((Class<?>) type.getClass())) {
			ParameterizedType genericReturnType = (ParameterizedType) type;
			return (Class<?>) genericReturnType.getActualTypeArguments()[0];
		}
		return (Class<?>) type;

	}

	private static boolean isNotStatic(Field field) {
		return !Modifier.isStatic(field.getModifiers());
	}

}
