package designpattern.strategy;

/**
 * 满减结算策略
 * @author laizy1991@gmail.com
 * @createDate 2015年8月17日
 *
 */
public class CashReturn extends CashSuper{

    private double moneyCondition;
    private double moneyReturn;
    
    public CashReturn(double moneyCond, double moneyReturn) {
        this.moneyCondition = moneyCond;
        this.moneyReturn = moneyReturn;
    }
    
    @Override
    public double acceptCash(double money) {
        return money - Math.floor(money / moneyCondition) * moneyReturn;
    }

}
