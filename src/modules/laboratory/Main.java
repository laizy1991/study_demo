package modules.laboratory;

import java.lang.instrument.Instrumentation;

/**
 * 字节码转换相关测试代码入口
 * @author laizy1991@gmail.com
 * @createDate 2015年8月10日
 *
 */
public class Main {

    public static void premain(String args, Instrumentation inst) {
        //inst.addTransformer(new MyTransformer());
    }
    
}
