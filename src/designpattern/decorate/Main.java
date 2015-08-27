package designpattern.decorate;

/**
 * 测试入口
 * @author laizy1991@gmail.com
 * @createDate 2015年8月27日
 *
 */
public class Main {

    public static void main(String[] args) {
        Person person = new Person("幽灵");
        Finery finery = new Finery();
        finery.decorate(person);
        
        TShirts tShirt = new TShirts();
        BigTrouser trouser = new BigTrouser();
        Sneaker sneaker = new Sneaker();
        
        tShirt.decorate(finery);
        trouser.decorate(tShirt);
        sneaker.decorate(trouser);
        
        sneaker.show();
    }
    
}
