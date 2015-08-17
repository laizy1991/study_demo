package designpattern.factory;

/**
 * 操作抽象类
 * @author laizy1991@gmail.com
 * @createDate 2015年8月17日
 *
 */
public abstract class Operation {

    protected double numberA;
    protected double numberB;
    public double getNumberA() {
        return numberA;
    }
    public void setNumberA(double numberA) {
        this.numberA = numberA;
    }
    public double getNumberB() {
        return numberB;
    }
    public void setNumberB(double numberB) {
        this.numberB = numberB;
    }
    
    public abstract double getResult(); 
}
