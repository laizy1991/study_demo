package instrument;

import model.Dog;

import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class MyMethodAdapter extends MethodAdapter {
    private String name;
    
    public MyMethodAdapter(MethodVisitor paramMethodVisitor, String methodName) {
        super(paramMethodVisitor);
        this.name = methodName;
    }

    /*
     * 执行顺序如下：
     * visit visitSource? visitOuterClass? ( visitAnnotation | visitAttribute )*( visitInnerClass | visitField | visitMethod )* visitEnd
     */
   
    @Override  
    public void visitCode() {  
        if(name.equals("myAge")) {
            mv.visitFieldInsn(Opcodes.GETSTATIC,  
                    Type.getInternalName(Dog.class), "timer", "J");  
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System",  
                    "currentTimeMillis", "()J");  
            mv.visitInsn(Opcodes.LSUB);  
            mv.visitFieldInsn(Opcodes.PUTSTATIC,  
                    Type.getInternalName(Dog.class), "timer", "J");
        } else {
            super.visitCode();
        }
    }  
  
    @Override  
    public void visitInsn(int opcode) {  
        if (opcode == Opcodes.RETURN && name.equals("myAge")) {  
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out",  
                    "Ljava/io/PrintStream;");  
            mv.visitFieldInsn(Opcodes.GETSTATIC,  
                    Type.getInternalName(Dog.class), "timer", "J");  
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System",  
                    "currentTimeMillis", "()J");  
            mv.visitInsn(Opcodes.LADD);  
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",  
                    "println", "(J)V");  
        }  
        mv.visitInsn(opcode);  
    }  

}
