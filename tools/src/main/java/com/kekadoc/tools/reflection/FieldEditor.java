package com.kekadoc.tools.reflection;

import java.lang.reflect.Field;

public class FieldEditor {

    private Field field;
    /** Объект в котором находиться Field */
    private Object objectOfField;

    public FieldEditor(Field field, Object objectOfField) {
        this.field = field;
        this.objectOfField = objectOfField;
    }
    public FieldEditor() {}

    public void setup(Field field, Object objectOfField) {
        if (this.field != null) lock();
        this.field = field;
        this.objectOfField = objectOfField;
    }

    public Field getField() {
        return field;
    }
    public Object getObjectOfField() {
        return objectOfField;
    }

    public void unlock() {
        this.field.setAccessible(true);
    }
    public void lock() {
        this.field.setAccessible(false);
    }

    public void set(Object o) {
        try {
            if (!field.isAccessible()) unlock();
            field.set(objectOfField, o);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("||| " + e + " " + objectOfField  + " " + o  + " " + " |||");
        }
    }

    public <T> T pullObject() {
        try {
            if (!field.isAccessible()) unlock();
            return (T) field.get(objectOfField);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
