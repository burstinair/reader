package burst.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Burst
 * Date: 13-4-24
 * Time: 下午6:39
 * To change this template use File | Settings | File Templates.
 */
public class TypeUtils {

    private static List<Class> value_types;
    private static DateFormat date_parser;

    static {
        value_types = new ArrayList<Class>();
        value_types.add(int.class);
        value_types.add(byte.class);
        value_types.add(short.class);
        value_types.add(long.class);
        value_types.add(CharSequence.class);
        value_types.add(boolean.class);
        value_types.add(Integer.class);
        value_types.add(Byte.class);
        value_types.add(Short.class);
        value_types.add(Long.class);
        value_types.add(Boolean.class);
        value_types.add(Date.class);

        date_parser = new SimpleDateFormat();
    }

    public static boolean isValueType(Class type) {
        for(Class v_type : value_types) {
            if(v_type.isAssignableFrom(type)) {
                return true;
            }
        }
        return false;
    }

    public static String serialize(Object value) {
        return value.toString();
    }

    public static <T> T parse(String str, Class type) throws ParseException {
        if(CharSequence.class.equals(type) || String.class.equals(type)) {
            return (T)str;
        }
        if(boolean.class.equals(type) || Boolean.class.equals(type)) {
            return (T) (Boolean) Boolean.parseBoolean(str);
        }
        if(int.class.equals(type) || Integer.class.equals(type)) {
            return (T) (Integer) Integer.parseInt(str);
        }
        if(double.class.equals(type) || Double.class.equals(type)) {
            return (T) (Double) Double.parseDouble(str);
        }
        if(float.class.equals(type) || Float.class.equals(type)) {
            return (T) (Float) Float.parseFloat(str);
        }
        if(long.class.equals(type) || Long.class.equals(type)) {
            return (T) (Long) Long.parseLong(str);
        }
        if(short.class.equals(type) || Short.class.equals(type)) {
            return (T) (Short) Short.parseShort(str);
        }
        if(byte.class.equals(type) || Byte.class.equals(type)) {
            return (T) (Byte) Byte.parseByte(str);
        }
        if(Date.class.equals(type)) {
            return (T) date_parser.parse(str);
        }
        return null;
    }

    public static PropertyInfo getProperty(Class type, String propertyName) {
        PropertyInfo res = new PropertyInfo();
        try {
            res.setField(type.getField(propertyName));
            res.setType(res.getField().getType());
            res.setProperty(false);
        } catch (NoSuchFieldException er) {
            boolean found = false;
            String firstCharToUpper = propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
            String getMethodName = "get" + firstCharToUpper;
            String setMethodName = "set" + firstCharToUpper;
            String isMethodName = "is" + firstCharToUpper;
            for(Method method : type.getMethods()) {
                if(method.getName().equals(getMethodName) && method.getParameterTypes().length == 0) {
                    res.setGetMethod(method);
                    res.setType(method.getReturnType());
                    found = true;
                } else if(method.getName().equals(setMethodName)) {
                    Class[] parameterTypes = method.getParameterTypes();
                    if(parameterTypes.length == 1) {
                        res.setSetMethod(method);
                        res.setType(parameterTypes[0]);
                        found = true;
                    }
                } else if(method.getName().equals(isMethodName) && method.getParameterTypes().length == 0) {
                    Class returnType = method.getReturnType();
                    if(returnType.equals(Boolean.class) || returnType.equals(boolean.class)) {
                        res.setGetMethod(method);
                        res.setType(returnType);
                        found = true;
                    }
                }
            }
            if(!found) {
                return null;
            }
            res.setProperty(true);
        }
        res.setName(propertyName);
        return res;
    }

    public static List<PropertyInfo> getProperties(Class type) {
        Map<String, PropertyInfo> added = new HashMap<String, PropertyInfo>();
        ArrayList<PropertyInfo> res = new ArrayList<PropertyInfo>();
        for(Field field : type.getFields()) {
            String name = field.getName();
            if(!added.containsKey(name)) {
                Class ctype = field.getType();
                PropertyInfo propertyInfo = new PropertyInfo();
                propertyInfo.setType(ctype);
                propertyInfo.setName(name);
                propertyInfo.setField(field);
                propertyInfo.setProperty(false);
                added.put(name, propertyInfo);
                res.add(propertyInfo);
            }
        }
        for(Method method : type.getMethods()) {
            String methodName = method.getName();
            String name = null;
            PropertyInfo propertyInfo = null;
            Class[] parameterTypes = method.getParameterTypes();
            Class returnType = method.getReturnType();
            if(methodName.startsWith("get") && Character.isUpperCase(methodName.charAt(3)) && parameterTypes.length == 0 && !methodName.equals("getClass")) {
                name = Character.toLowerCase(methodName.charAt(3)) + methodName.substring(4);
                if(added.containsKey(name)) {
                    propertyInfo = added.get(name);
                    if(!propertyInfo.isProperty() || propertyInfo.getGetMethod() != null || !propertyInfo.getType().equals(returnType)) {
                        continue;
                    }
                } else {
                    propertyInfo = new PropertyInfo();
                    propertyInfo.setName(name);
                    propertyInfo.setProperty(true);
                    propertyInfo.setType(returnType);
                    res.add(propertyInfo);
                    added.put(name, propertyInfo);
                }
                propertyInfo.setGetMethod(method);
            } else if(methodName.startsWith("set") && Character.isUpperCase(methodName.charAt(3)) && parameterTypes.length == 1) {
                name = Character.toLowerCase(methodName.charAt(3)) + methodName.substring(4);
                if(added.containsKey(name)) {
                    propertyInfo = added.get(name);
                    if(!propertyInfo.isProperty() || propertyInfo.getSetMethod() != null || !propertyInfo.getType().equals(parameterTypes[0])) {
                        continue;
                    }
                } else {
                    propertyInfo = new PropertyInfo();
                    propertyInfo.setName(name);
                    propertyInfo.setProperty(true);
                    propertyInfo.setType(parameterTypes[0]);
                    res.add(propertyInfo);
                    added.put(name, propertyInfo);
                }
                propertyInfo.setSetMethod(method);
            } else if(methodName.startsWith("is") && Character.isUpperCase(methodName.charAt(2)) &&
                    parameterTypes.length == 0 && (Boolean.class.isAssignableFrom(returnType) || boolean.class.isAssignableFrom(returnType))) {
                name = Character.toLowerCase(methodName.charAt(2)) + methodName.substring(3);
                if(added.containsKey(name)) {
                    propertyInfo = added.get(name);
                    if(!propertyInfo.isProperty() || propertyInfo.getGetMethod() != null || !propertyInfo.getType().equals(returnType)) {
                        continue;
                    }
                } else {
                    propertyInfo = new PropertyInfo();
                    propertyInfo.setName(name);
                    propertyInfo.setProperty(true);
                    propertyInfo.setType(returnType);
                    res.add(propertyInfo);
                    added.put(name, propertyInfo);
                }
                propertyInfo.setGetMethod(method);
            }
        }
        return res;
    }
}
