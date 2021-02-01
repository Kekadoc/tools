package com.kekadoc.tools.reflection;

import com.kekadoc.tools.storage.Iteration;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * ReflectionUtils
 */
public class ReflectionUtils {
    private static final String TAG = "QeReflection-TAG";

    public interface AnnotationFinder<T extends Annotation> {
        void onFind(T annotation, Field field, Object object);
        void onCollection(T annotation, Field field, Collection<?> collection);
        void onMap(T annotation, Field field, Map<?, ?> map);
        void onArray(T annotation, Field field, Object[] array);
    }

    /**
     * Find all annotation
     */
    public static <T extends Annotation> void findAllAnnotation(Object target, Class<T> annotationClass, AnnotationFinder<T> finder) {
        for (Field field : getAllFields(target.getClass())) {
            if (field.isAnnotationPresent(annotationClass)) {
                T annotation = Objects.requireNonNull(field.getAnnotation(annotationClass));
                boolean access = field.isAccessible();
                field.setAccessible(true);
                try {
                    Object o = field.get(target);
                    finder.onFind(annotation, field, o);
                    if (field.getClass().isArray()) finder.onArray(annotation, field, (Object[]) o);
                    else if (Collection.class.isAssignableFrom(field.getType())) finder.onCollection(annotation, field, (Collection<?>) o);
                    else if (Map.class.isAssignableFrom(field.getType())) finder.onMap(annotation, field, (Map<?, ?>) o);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                field.setAccessible(access);
            }
        }
    }

    public static <T> T newInstance(String className, Class<?>[] argsClass, Object[] args) {
        try {
            Class<?> clazz = Class.forName(className);
            Constructor<?> constructor = clazz.getConstructor(argsClass);
            return (T) constructor.newInstance(args);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(className + " " + Arrays.toString(args) + " " + e);
        }
    }

    public static Object getObjectFromAccessibleField(Field field, Object objOfField) {
        try {
            return field.get(objOfField);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public static Object getObjectFromField(Field field, Object objOfField) {
        boolean access = field.isAccessible();
        field.setAccessible(true);
        try {
            Object o = field.get(objOfField);
            field.setAccessible(access);
            return o;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static boolean isHaveDeclaredAnnotation(Class<?> clazz, Class<?> annotation) {
        for (Annotation a : clazz.getDeclaredAnnotations()) {
            if (annotation.getClass().isAssignableFrom(a.getClass()))
                return true;
        }
        return false;
    }
    public static boolean isHaveAnnotation(Class<?> clazz, Class<?> annotation) {
        for (Annotation a : clazz.getAnnotations()) {
            if (annotation.getClass().isAssignableFrom(a.getClass()))
                return true;
        }
        return false;
    }

    public static boolean isHaveAnnotation(Method method, Class<?> annotation) {
        for (Annotation a : method.getAnnotations()) {
            if (annotation.isAssignableFrom(a.getClass())) return true;
        }
        return false;
    }
    public static boolean isHaveDeclaredAnnotation(Method method, Class<?> annotation) {
        for (Annotation a : method.getDeclaredAnnotations()) {
            if (annotation.isAssignableFrom(a.getClass())) return true;
        }
        return false;
    }

    public static boolean isHaveAnnotation(Field field, Class<?> annotation) {
        for (Annotation a : field.getAnnotations()) {
            if (annotation.isAssignableFrom(a.getClass())) return true;
        }
        return false;
    }
    public static boolean isHaveDeclaredAnnotation(Field field, Class<?> annotation) {
        for (Annotation a : field.getDeclaredAnnotations()) {
            if (annotation.isAssignableFrom(a.getClass())) return true;
        }
        return false;
    }

    public static Method getMethod(Class<?> clazz, String name, Class<?>...params) {
        try {
            return clazz.getMethod(name, params);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
    public static Field getField(Class<?> clazz, String name) {
        try {
            return clazz.getField(name);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public static Annotation[] getAllAnnotationClass(Class<?> clazz) {
        ArrayList<Annotation> list = new ArrayList<>();

        while (clazz != null) {
            list.addAll(Arrays.asList(clazz.getAnnotations()));
            clazz = clazz.getSuperclass();
        }

        return list.toArray(new Annotation[0]);
    }

    public static void getAllFields(Class<?> clazz, Iteration.Single<Field> field) {
        while (clazz.getSuperclass() != null) {
            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) field.iteration(fields[i]);
            clazz = clazz.getSuperclass();
        }
    }

    public static Field[] getAllFields(Class<?> clazz) {
        ArrayList<Field> fields = new ArrayList<>();
        while (clazz.getSuperclass() != null) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        Field[] fieldsArray = new Field[fields.size()];
        return fields.toArray(fieldsArray);
    }

    public static Class<?>[] getAllInterfaces(Class<?> clazz) {
        ArrayList<Class<?>> interfaces = new ArrayList<>();
        while (clazz.getSuperclass() != null) {
            interfaces.addAll(Arrays.asList(clazz.getInterfaces()));
            clazz = clazz.getSuperclass();
        }
        return interfaces.toArray(new Class<?>[0]);
    }

}
