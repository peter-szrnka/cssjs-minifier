package hu.szrnkapeter.cssjsminifier.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class TestUtils {

	public static <S> void testPrivateConstructor(Class<S> singletonClass) throws SecurityException, NoSuchMethodException,
			InstantiationException, IllegalAccessException, InvocationTargetException {
		final Constructor<S> constructor = singletonClass.getDeclaredConstructor();
		constructor.setAccessible(true);
		constructor.newInstance();
		constructor.setAccessible(false);
	}
}