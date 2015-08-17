package designpattern.strategy;

public class Main {

    public static void main(String[] args) {
        CashContext context = new CashContext(CashCode.NORMAL);
        System.err.println(context.getReslt(500));
        context = new CashContext(CashCode.REBATE);
        System.err.println(context.getReslt(500));
        context = new CashContext(CashCode.RETURN);
        System.err.println(context.getReslt(500));
    }
    
}
