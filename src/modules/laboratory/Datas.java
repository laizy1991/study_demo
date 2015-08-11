package modules.laboratory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 收集代码运行时的一些数据
 * @author laizy1991@gmail.com
 * @createDate 2015年8月10日
 *
 */
public class Datas {
    /**
     * 注入类数
     */
    public static AtomicInteger instrumentClassCount = new AtomicInteger(0);
}
