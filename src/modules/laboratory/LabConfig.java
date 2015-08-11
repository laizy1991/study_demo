package modules.laboratory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * 配置
 * @author laizy1991@gmail.com
 * @createDate 2015年8月10日
 *
 */
public class LabConfig {
    /**
     * 配置文件名
     */
    private static final String CONFIG_FILE_NAME = "lab.properties";
    
    private boolean debugMode;

    public LabConfig() {
        //此时配置文件中的debug参数还未读取，因此使用-Dtprofiler.debug=true来读取，用于开发时调试
        boolean debug = "true".equalsIgnoreCase(System.getProperty("lab.debug")); 
        /*
         * 查找顺序：
         * 1. 系统参数-Dlab.properties=/path/lab.properties
         * 2. 当前文件夹下的lab.properties
         * 3. 默认jar包中的lab.properties
         */
        String specifiedConfigFileName = System.getProperty(CONFIG_FILE_NAME);
        File configFiles[] = {
                specifiedConfigFileName == null ? null : new File(specifiedConfigFileName), 
                        new File(CONFIG_FILE_NAME) 
        };

        for (File file : configFiles){
            if (file != null && file.exists() && file.isFile()) {
                if (debug){
                    System.out.println(String.format("load configuration from \"%s\".", file.getAbsolutePath()));
                }
                parseProperty(file);
            }
        }
        
    }
    
    private void parseProperty(File file) {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(file));
            loagConfig(properties);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loagConfig(Properties properties) {
        debugMode = Boolean.valueOf(properties.getProperty("debugMode"));
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }
}
