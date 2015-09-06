package designpattern.factorymethod;

/**
 * 减法运算类实例化工厂
 * @author laizy1991@gmail.com
 * @createDate 2015年9月6日
 *
 */
public class OperationSubFactory implements OperationFactory {

    @Override
    public Operation createOperation() {
        return new OperationSub();
    }

}
