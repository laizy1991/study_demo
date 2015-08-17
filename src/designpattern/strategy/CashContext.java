package designpattern.strategy;

public class CashContext {

    private CashSuper cs;
    
    public CashContext(int type) {
        switch (type) {
        case CashCode.REBATE:
            cs = new CashRebate(0.75);
            break;
        case CashCode.RETURN:
            cs = new CashReturn(200, 50);
            break;
        default:
            cs = new CashNormal();
            break;
        }
    }
    
    public double getReslt(double money) {
        return cs.acceptCash(money);
    }
}
