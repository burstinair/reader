package burst.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: Burst
 * Date: 13-4-24
 * Time: 下午7:41
 * To change this template use File | Settings | File Templates.
 */
public class PropertyInfo {

    public void setValue(Object target, Object value) throws InvocationTargetException, IllegalAccessException {
        if(isProperty) {
            setMethod.invoke(target, value);
        }
        field.set(target, value);
    }

    public Object getValue(Object target) throws InvocationTargetException, IllegalAccessException {
        if(isProperty) {
            return getMethod.invoke(target);
        }
        return field.get(target);
    }

    public void setProperty(boolean property) {
        isProperty = property;
    }

    public Annotation getAnnotationOnWrite(Class annotationType) {
        try {
            if(isProperty) {
                return setMethod.getAnnotation(annotationType);
            } else {
                return field.getAnnotation(annotationType);
            }
        } catch (Throwable ex) { }
        return null;
    }

    public Annotation getAnnotationOnRead(Class annotationType) {
        try {
            if(isProperty) {
                return getMethod.getAnnotation(annotationType);
            } else {
                return field.getAnnotation(annotationType);
            }
        } catch (Throwable ex) { }
        return null;
    }

    public Annotation getAnnotation(Class annotationType) {
        try {
            if(isProperty) {
                Annotation res = null;
                try {
                    res = getMethod.getAnnotation(annotationType);
                } catch (Throwable ex) { }
                if(res != null) {
                    return res;
                }
                return setMethod.getAnnotation(annotationType);
            } else {
                return field.getAnnotation(annotationType);
            }
        } catch (Throwable ex) { }
        return null;
    }

    private boolean isProperty;
    private Method getMethod;
    private Method setMethod;
    private String name;
    private Class type;

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    private Field field;

    public boolean isProperty() {
        return isProperty;
    }

    public Method getGetMethod() {
        return getMethod;
    }

    public void setGetMethod(Method getMethod) {
        this.getMethod = getMethod;
    }

    public Method getSetMethod() {
        return setMethod;
    }

    public void setSetMethod(Method setMethod) {
        this.setMethod = setMethod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }
}
