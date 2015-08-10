package modules.reflect.interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 拦截器，在方法调用之前添加一些必要的操作
 * @author laizy1991@gmail.com
 * @createDate 2015年8月10日
 *
 */
public class MyInterceptor implements InvocationHandler {
    private Object target;
    public MyInterceptor(Object target) {
        this.target = target;
    }
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        return method.invoke(target, args);
    }

    private void before() {
        System.out.println("before method invoke.");
    }
    
}
