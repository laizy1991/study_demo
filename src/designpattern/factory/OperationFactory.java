package designpattern.factory;

/**
 * 运算类实例化工厂
 * @author laizy1991@gmail.com
 * @createDate 2015年8月17日
 *
 */
public class OperationFactory {

    /**
     * 根据运算符创建对应的实体
     * @param operate
     * @return
     */
    public static Operation createOperation(char operate) {
        Operation oper = null;
        switch (operate) {
        case '+':
            oper = new OperationAdd();
            break;
        case '-':
            oper = new OperationSub();
            break;
        case '*':
            oper = new OperationMul();
            break;
        case '/':
            oper = new OperationDiv();
            break;
        default:
            System.err.println("操作类型不合法");
            break;
        }
        
        return oper;
    }
}
