package modules.laboratory;

/**
 * 管理类
 * @author laizy1991@gmail.com
 * @createDate 2015年8月10日
 *
 */
public class Manager {
    private static Manager maneger = new Manager();
    private LabConfig config;
    private boolean debugMode = false;
    
    private Manager(){}
    
    public Manager instance() {
        return maneger;
    }
    
    /**
     * 初始化
     */
    public void init() {
        config = new LabConfig();
        debugMode = config.isDebugMode();
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }
    
}
