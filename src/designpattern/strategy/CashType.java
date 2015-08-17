package designpattern.strategy;

/**
 * 结算策略类型
 * @author laizy1991@gmail.com
 * @createDate 2015年8月17日
 *
 */
public enum CashType {

    NORMAL(1, "不促销，原价"),
    REBATE(2, "7.5折"),
    RETURN(3, "满200减50");
    
    private CashType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    
    private int code;
    private String desc;
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    
}
