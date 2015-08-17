package designpattern.factory;

public class Main {

    public static void main(String[] args) {
        Operation oper = OperationFactory.createOperation('+');
        oper.numberA = 9.8;
        oper.numberB = 2.0;
        System.out.println(oper.getResult());
        oper = OperationFactory.createOperation('-');
        oper.numberA = 9.8;
        oper.numberB = 2.0;
        System.out.println(oper.getResult());
        oper = OperationFactory.createOperation('*');
        oper.numberA = 9.8;
        oper.numberB = 2.0;
        System.out.println(oper.getResult());
        oper = OperationFactory.createOperation('/');
        oper.numberA = 9.8;
        oper.numberB = 0.0;
        System.out.println(oper.getResult());
        oper = OperationFactory.createOperation('/');
        oper.numberA = 9.8;
        oper.numberB = 2.0;
        System.out.println(oper.getResult());
    }
}
