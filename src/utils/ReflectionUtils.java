package utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import model.Dog;

/**
 * 反射相关操作
 * @author laizy1991@gmail.com
 * @createDate 2015年8月8日
 *
 */
public class ReflectionUtils {

    /**
     * 通过setter设置某个成员变量的值
     * @param obj   需要操作的对象
     * @param field 需要操作的变量的名字   
     * @param val   变量的值
     */
    public static boolean setFieldValue(Object obj, String field, Object val) {
        Class<? extends Object> clazz = obj.getClass();
        //获取所有的Method,包括public、protect、default、private，但不返回超类或超接口的的方法
        Method[] methods = clazz.getDeclaredMethods();
        //获取所有的public方法，包括超类、超接口
        //Method[] methods = clazz.getMethods();
        for(Method method : methods) {
            String methodName = method.getName();
            if(methodName.equalsIgnoreCase("set" + field)) {
                try {
                    method.invoke(obj, val);
                } catch (IllegalArgumentException e) {
                    //参数不合法
                    e.printStackTrace();
                    return false;
                } catch (IllegalAccessException e) {
                    //无访问权限
                    e.printStackTrace();
                    return false;
                } catch (InvocationTargetException e) {
                    //目标方法抛异常
                    e.printStackTrace();
                    return false;
                }
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * 通过getter返回一个某个成员变量的值
     * @param obj   需要操作的对象
     * @param field 需要操作的成员变量
     * @return
     */
    public static Object getFieldValue(Object obj, String field) {
        Class<? extends Object> clazz = obj.getClass();
        //获取所有的Method,包括public、protect、default、private，但不返回超类或超接口的的方法
        Method[] methods = clazz.getDeclaredMethods();
        //获取所有的public方法，包括超类、超接口
        //Method[] methods = clazz.getMethods();
        for(Method method : methods) {
            String methodName = method.getName();
            if(methodName.equalsIgnoreCase("get" + field)) {
                try {
                    return method.invoke(obj);
                } catch (IllegalArgumentException e) {
                    //参数不合法
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    //无访问权限
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    //目标方法抛异常
                    e.printStackTrace();
                }
                break;
            }
        }
        
        return null;
    }
    
    public static void main(String[] args) {
        Dog dog = new Dog();
        setFieldValue(dog, "name", "xiaoyun");
        setFieldValue(dog, "age", 12);
        System.out.println(getFieldValue(dog, "name"));
        System.out.println(getFieldValue(dog, "age"));
    }
}
