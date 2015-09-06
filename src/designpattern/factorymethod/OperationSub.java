package designpattern.factorymethod;

/**
 * 减法运算
 * @author laizy1991@gmail.com
 * @createDate 2015年8月17日
 *
 */
public class OperationSub extends Operation {

    @Override
    public double getResult() {
        return numberA - numberB;
    }

}
