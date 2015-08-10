package modules.reflect;

import java.lang.reflect.Proxy;

import model.Animal;
import model.Dog;
import modules.reflect.interceptor.MyInterceptor;

/**
 * 动态代理测试类
 * @author laizy1991@gmail.com
 * @createDate 2015年8月10日
 *
 */
public class ProxyTest {

    public static void main(String[] args) {
        Animal animal = new Dog("小丘", 2);
        MyInterceptor interceptor = new MyInterceptor(animal);
        Animal animalProxy = (Animal)Proxy.newProxyInstance(animal.getClass().getClassLoader(), animal.getClass().getInterfaces(), interceptor);
        System.out.println();
        int ret = animalProxy.compareTo(new Dog("人马", 6));
        System.out.println("result : " + ret);
        
        Object proxy = Proxy.newProxyInstance(animal.getClass().getClassLoader(), animal.getClass().getInterfaces(), interceptor);
        System.out.println(proxy instanceof Dog);
        System.out.println(proxy instanceof Animal);
    }
}
