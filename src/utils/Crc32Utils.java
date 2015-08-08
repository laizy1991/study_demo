package utils;

import java.nio.charset.Charset;
import java.util.zip.CRC32;

import constants.GlobalConstants;

/**
 * CRC32校验，多线程安全
 * Cyclic Redundancy Check -- 循环冗余校验
 * @author laizy1991@gmail.com
 * @createDate 2015年8月4日
 *
 */
public class Crc32Utils {

    /**
     * 返回input对应的crc32校验和
     * @param input
     * @return
     */
    public static long getCheckSum(String input) {
        if(input == null) {
            return 0;
        }
        
        byte[] data = input.getBytes(Charset.forName(GlobalConstants.CharsetsName.UTF8));
        CRC32 crc32 = new CRC32();
        crc32.update(data);
        return crc32.getValue();
    }
    
    public static void main(String[] args) {
        System.out.println(getCheckSum("laizy1991@gmail.com"));
    }
}
