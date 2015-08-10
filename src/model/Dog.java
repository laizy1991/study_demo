package model;

public class Dog implements Animal {

    private String name;
    
    private Integer age;

    public Dog(){}
    
    public Dog(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public int compareTo(Animal anotherAnimal) {
        if(anotherAnimal == null) {
            return -1;
        }
        Dog anotherDog = null;
        if(anotherAnimal instanceof Dog) {
            anotherDog = (Dog)anotherAnimal;
        } else {
            return -1;
        }
        
        int thisVal = this.age.intValue();
        int anotherVal = anotherDog.age.intValue();
        return thisVal<anotherVal ? -1 : (thisVal == anotherVal ? 0 : 1);
    }
    
}
