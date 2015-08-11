package utils;

/**
 * 字符串辅助类
 * @author laizy1991@gmail.com
 * @createDate 2015年8月10日
 *
 */
public class StringUtils {

    public static boolean isEmpty(String input) {
        if(input == null || input.isEmpty()) {
            return true;
        }
        
        return false;
    }
    
    public static boolean isBlank(String input) {
        if(input == null || input.trim().isEmpty()) {
            return true;
        }
        
        return false;
    }
    
}
