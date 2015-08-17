package designpattern.factory;

/**
 * 除法运算
 * @author laizy1991@gmail.com
 * @createDate 2015年8月17日
 *
 */
public class OperationDiv extends Operation {

    @Override
    public double getResult() {
        if(numberB == 0) {
            System.err.println("除数不能为0");
            return Double.NaN;
        }
        
        return numberA / numberB;
    }

}
