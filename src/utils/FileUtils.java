package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件操作辅助类
 * @author laizy1991@gmail.com
 * @createDate 2015年8月10日
 *
 */
public class FileUtils {

    /**
     * 
     * @param relativePath
     * @param content
     * @throws IOException 
     */
    public static void write(String relativePath, byte[] content) throws IOException {
        if(StringUtils.isBlank(relativePath) || content == null) {
            return;
        }
        
        String dir = System.getProperty("user.dir");
        File file = new File(new File(dir), relativePath);
        File pFile = new File(file.getParent());
        if(!pFile.exists() || pFile.isFile()) {
            pFile.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(content);
        fos.flush();
        fos.close();
    }
    
}
