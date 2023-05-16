package client.staff;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class GeneralizedFlistTest extends FlistTest {
    private final Constructor<? extends Flist<?>> constructor;

    public GeneralizedFlistTest(Class<? extends Flist<?>> clazz) throws NoSuchMethodException {
        constructor = clazz.getConstructor(Object[].class);
    }

    @SafeVarargs
    @Override
    public final <T> Flist<T> buildFlist(T... items) {
        Object[] params = {items};
        try {
            return (Flist<T>) constructor.newInstance(params);
        } catch (InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}