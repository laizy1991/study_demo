package instrument;

import model.Dog;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import utils.FileUtils;
import core.GeneratorClassLoader;

public class MyTransformerTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        try {
            ClassReader cr = new ClassReader("model.Dog");
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            ClassAdapter adapter = new MyClassAdapter(cw, Dog.class.getName());
            cr.accept(adapter, 0);
            byte[] classfileBuffer = cw.toByteArray();
            FileUtils.write("/runtime/Dog.class", classfileBuffer);
            
            GeneratorClassLoader classLoader = new GeneratorClassLoader();
            Class<?> clazz = classLoader.defineClassFromClassFile(  
                    "model.Dog", classfileBuffer);  
            Object obj = clazz.newInstance();  
            System.out.println(clazz.getDeclaredMethod("getName").invoke(obj));  
            clazz.getDeclaredMethod("myAge").invoke(obj);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
