package designpattern.factorymethod;

public class Main {

    public static void main(String[] args) {
        OperationFactory addFactory = new OperationAddFactory();
        Operation oper = addFactory.createOperation();
        oper.numberA = 9.8;
        oper.numberB = 2.0;
        System.out.println(oper.getResult());
        
        OperationFactory subFactory = new OperationSubFactory();
        oper = subFactory.createOperation();
        oper.numberA = 9.8;
        oper.numberB = 2.0;
        System.out.println(oper.getResult());
        
        OperationFactory mulFactory = new OperationMulFactory();
        oper = mulFactory.createOperation();
        oper.numberA = 9.8;
        oper.numberB = 2.0;
        System.out.println(oper.getResult());
        
        OperationFactory divFactory = new OperationDivFactory();
        oper = divFactory.createOperation();
        oper.numberA = 9.8;
        oper.numberB = 0.0;
        System.out.println(oper.getResult());
        
        oper = divFactory.createOperation();
        oper.numberA = 9.8;
        oper.numberB = 2.0;
        System.out.println(oper.getResult());
    }
}
