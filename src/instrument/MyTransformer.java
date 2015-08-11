package instrument;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import modules.laboratory.Datas;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

/**
 * 字节码转换类
 * @author laizy1991@gmail.com
 * @createDate 2015年8月10日
 *
 */
public class MyTransformer implements ClassFileTransformer {

    /**
     * 转换提供的类文件，并返回转换后的结果
     */
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
            ProtectionDomain protectionDomain, byte[] classfileBuffer)
            throws IllegalClassFormatException {
        //记录注入的类数
        Datas.instrumentClassCount.getAndIncrement();
        
        try {
            ClassReader cr = new ClassReader(classfileBuffer);
            //自动计算局部变量和操作数栈的大小
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            ClassAdapter adapter = new MyClassAdapter(cw, className);
            cr.accept(adapter, 0);
            return cw.toByteArray();
        } catch(Throwable e) {
            e.printStackTrace();
            return classfileBuffer;
        }
    }

    
}
