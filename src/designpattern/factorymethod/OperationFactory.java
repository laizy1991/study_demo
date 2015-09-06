package designpattern.factorymethod;

/**
 * 运算类实例化工厂接口
 * @author laizy1991@gmail.com
 * @createDate 2015年9月6日
 *
 */
public interface OperationFactory {

    /**
     * 根据运算符创建对应的实体
     * @param operate
     * @return
     */
    public abstract Operation createOperation();
}
