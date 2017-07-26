package me.ibore.library.mvp;

import java.lang.reflect.ParameterizedType;

/**
 * description: 反射工具类
 * author: Ibore Xie
 * date: 2017-04-03 09:57
 * website: ibore.me
 */
class ClassUtil {
    public static <T> T getClass(Object object, int position) {
        try {
            return ((Class<T>) ((ParameterizedType) (object.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[position])
                    .newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Class<?> forName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
