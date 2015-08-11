package instrument;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * 类配置器
 * @author laizy1991@gmail.com
 * @createDate 2015年8月10日
 *
 */
public class MyClassAdapter extends ClassAdapter {

    public MyClassAdapter(ClassVisitor visitor, String className) {
        super(visitor);
        System.out.println("className : " + className);
    }

    //执行顺序如下：
    //visit visitSource? visitOuterClass? ( visitAnnotation | visitAttribute )*( visitInnerClass | visitField | visitMethod )* visitEnd 
    
    @Override
    /**
     * 访问类的头部信息
     */
    public void visit(int version, int access, String name, String signature,
            String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        System.out.println(String.format("visit : %s - %s - %s - %s - %s - %s", version, access, name, signature, superName, interfaces));
    }

    @Override
    public void visitSource(String source, String debug) {
        super.visitSource(source, debug);
        System.out.println("class file name: " + source);
    }

    @Override
    public void visitOuterClass(String owner, String name, String desc) {
        super.visitOuterClass(owner, name, desc);
        System.out.println(String.format("visitOuterClass : %s - %s - %s", owner, name, desc));
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        System.out.println(String.format("visitAnnotation : %s - %s", desc, visible));
        return super.visitAnnotation(desc, visible);
    }

    @Override
    public void visitAttribute(Attribute attr) {
        super.visitAttribute(attr);
        System.out.println("attr : " + attr);
    }

    @Override
    public void visitInnerClass(String name, String outerName, String innerName,
            int access) {
        super.visitInnerClass(name, outerName, innerName, access);
        System.out.println(String.format("visitInnerClass : %s - %s - %s - %s", name, outerName, innerName, access));
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc,
            String signature, Object value) {
        System.out.println(String.format("visitField : %s - %s - %s - %s - %s", access, name, desc, signature, value));
        if(name.equals("age")) {
            value = 100;
        } else if(name.equals("name")) {
            value = "小丘";
        }
        return super.visitField(access, name, desc, signature, value);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc,
            String signature, String[] exceptions) {
        System.out.println(String.format("visitMethod : %s - %s - %s - %s - %s", access, name, desc, signature, exceptions));
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        MethodAdapter ma = new MyMethodAdapter(mv, name);
        return ma;
    }


    @Override  
    public void visitEnd() {  
        cv.visitField(Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, "timer", "J",  
                null, null);
        super.visitEnd();
    }  

}
