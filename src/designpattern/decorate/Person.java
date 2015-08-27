package designpattern.decorate;

public class Person {
    private String name;
    
    public Person(){}
    
    public Person(String name) {
        this.name = name;
    }
    
    public void show() {
        System.out.println(String.format("【%s】的装扮：", name));
    }
}
