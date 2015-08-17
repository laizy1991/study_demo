package designpattern.strategy;

/**
 * 打折结算策略
 * @author laizy1991@gmail.com
 * @createDate 2015年8月17日
 *
 */
public class CashRebate extends CashSuper {
    
    private double rebate;
    
    public CashRebate(double rebate) {
        this.rebate = rebate;
    }
    
    @Override
    public double acceptCash(double money) {
        return money * rebate;
    }

}
