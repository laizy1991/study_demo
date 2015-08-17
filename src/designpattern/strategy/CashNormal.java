package designpattern.strategy;

/**
 * 没任何促销活动的结算策略
 * @author laizy1991@gmail.com
 * @createDate 2015年8月17日
 *
 */
public class CashNormal extends CashSuper {

    @Override
    public double acceptCash(double money) {
        return money;
    }

}
