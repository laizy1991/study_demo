package designpattern.factorymethod;

/**
 * 加法运算类实例化工厂
 * @author laizy1991@gmail.com
 * @createDate 2015年9月6日
 *
 */
public class OperationAddFactory implements OperationFactory {

    @Override
    public Operation createOperation() {
        return new OperationAdd();
    }

}
